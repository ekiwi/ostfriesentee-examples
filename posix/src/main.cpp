/*
 * main.c
 * 
 * Copyright (c) 2008-2010 CSIRO, Delft University of Technology.
 * 
 * This file is part of Darjeeling.
 * 
 * Darjeeling is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Darjeeling is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with Darjeeling.  If not, see <http://www.gnu.org/licenses/>.
 */
 

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <stdarg.h>
#include <iostream>

extern "C"
{

#include "jlib_base.h"
#include "jlib_ostfriesentee.h"

#include "types.h"
#include "vm.h"
#include "heap.h"
#include "execution.h"
#include "config.h"

#include "pointerwidth.h"
}

#include <hpp/ostfriesentee.hpp>
using namespace ostfriesentee;

char * ref_t_base_address;

extern unsigned char di_archive_data[];
extern size_t di_archive_size;

uint8_t mem[MEMSIZE];

int main(int /*argc*/,char* /*argv*/[])
{

	// initialise memory manager
	dj_mem_init(mem, MEMSIZE);
	ref_t_base_address = (char*)mem - 42;

	// Create a new VM
	Vm vm;
	vm.makeActiveVm();

	dj_named_native_handler handlers[] = {
			{ "base", &base_native_handler },
			{ "ostfriesentee", &ostfriesentee_native_handler }
		};

	int length = sizeof(handlers)/ sizeof(handlers[0]);
	dj_archive archive;
	archive.start = (dj_di_pointer)di_archive_data;
	archive.end = (dj_di_pointer)(di_archive_data + di_archive_size);

	vm.loadInfusionArchive(archive, handlers, length);

	// pre-allocate an OutOfMemoryError object
	dj_object* obj = vm.createSysLibObject(BASE_CDEF_java_lang_OutOfMemoryError);
	dj_mem_setPanicExceptionObject(obj);

	// start the main execution loop
	vm.run();

	std::cout << std::endl << "---------------------------" << std::endl;

	// try to find events.multi.Node class
	Infusion inf = vm.firstInfusion();
	while(inf.isValid()) {
		std::cout << "Infusion: " << inf.getName() << std::endl;
		List classes = inf.getClassList();
		std::cout << "Number of classes: " << static_cast<int>(classes.getSize()) << std::endl;
		List methods = inf.getMethodImplementationList();
		std::cout << "Number of methods: " << static_cast<int>(methods.getSize()) << std::endl;

		inf = inf.next();
	}

	return 0;
}

extern "C"
int debug_printf(const char * format, ...) {
	va_list ap;
	va_start(ap, format);

	vprintf(format, ap);

	va_end(ap);

	return 0;
}


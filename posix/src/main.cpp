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
#include <cstring>

extern "C"
{

#include "jlib_base.h"
#include "jlib_ostfriesentee.h"
#include "jlib_object.h"

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


std::ostream &operator<<(std::ostream &os, dj_local_id const &id) {
	os << "(" << static_cast<uint32_t>(id.infusion_id)
	   << ", " << static_cast<uint32_t>(id.entity_id) << ")";
	return os;
}

std::ostream &operator<<(std::ostream &os, String const &str) {
	os.write(str.data, str.length);
	return os;
}

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

/*
	std::cout << std::endl << "---------------------------" << std::endl;

	// list some information that can be gathered from the infusions
	Infusion inf = vm.firstInfusion();
	while(inf.isValid()) {
		std::cout << std::endl << "Infusion: " << inf.getName() << std::endl;

		ClassList classes = inf.getClassList();
		std::cout << "Number of classes: " << static_cast<int>(classes.getSize()) << std::endl;
		for(uint8_t ii = 0; ii < classes.getSize(); ++ii) {
			ClassDefinition def = classes.getElement(ii);
			int numberOfInterfaces = def.getNumberOfInterfaces();
			std::cout << "Superclass: " << def.getSuperClass() << std::endl;
			std::cout << "Name: " << def.getNameId() << std::endl;
			std::cout << "Interfaces: " << numberOfInterfaces << std::endl;
		}

		List methods = inf.getMethodImplementationList();
		std::cout << "Number of methods: " << static_cast<int>(methods.getSize()) << std::endl;

		StringTable strings = inf.getStringTable();
		std::cout << "Number of strings: " << static_cast<int>(strings.getSize()) << std::endl;
		for(uint16_t ii = 0; ii < strings.getSize(); ++ii) {
			std::cout << strings.getString(ii) << std::endl;
		}

		inf = inf.next();
	}


	std::cout << std::endl << "---------------------------" << std::endl;
	std::cout << "searching for infusion `object`..... ";
*/
	// fin events infusion
	Infusion inf = vm.firstInfusion();
	while(inf.isValid()) {
		if(strcmp(inf.getName(), "object") == 0) {
			std::cout << "found!" << std::endl;
			break;
		}
		inf = inf.next();
	}

	// try to create an instance of SimpleObject class

	// TODO: look at dj_vm_runClassInitialisers

	// create object in memory
	dj_global_id simple_id{inf.getUnderlying(), OBJECT_CDEF_SimpleObject};
	uint8_t runtime_id = dj_global_id_getRuntimeClassId(simple_id);
	dj_di_pointer classDef = vm.getRuntimeClassDefinition(runtime_id);
	dj_object* simple = dj_object_create(runtime_id,
			dj_di_classDefinition_getNrRefs(classDef),
			dj_di_classDefinition_getOffsetOfFirstReference(classDef)
			);
	dj_mem_addSafePointer((void**)&simple);

	// create thread and frame from which to call constructor
	dj_thread* thread = dj_thread_create();
	vm.addThread(thread);

	int16_t intParams[4];
	intParams[0] = (200 >>  0) & 0xffff;
	intParams[1] = (200 >> 16) & 0xffff;
	intParams[2] = (300 >>  0) & 0xffff;
	intParams[3] = (300 >> 16) & 0xffff;
	ref_t refParams[1] = { VOIDP_TO_REF(simple) };

	dj_global_id simple_init{inf.getUnderlying(), OBJECT_MDEF_void__init__int_int};
	dj_exec_callMethodFromNative(simple_init, thread, refParams, intParams);

	dj_exec_run(1000);
	//vm.run();
	//dj_exec_run(1000);




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


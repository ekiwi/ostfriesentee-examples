/*
 * main.c
 *
 * Copyright (c) 2015 Kevin Laeufer <kevin.laeufer@rwth-aachen.de>
 *
 * This file is part of Ostfriesentee.
 *
 * Ostfriesentee is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Ostfriesentee is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Ostfriesentee.  If not, see <http://www.gnu.org/licenses/>.
 */


#include "jlib_base.h"
#include "jlib_ostfriesentee.h"

#include "types.h"
#include "vm.h"
#include "heap.h"
#include "execution.h"
#include "config.h"

#include "pointerwidth.h"
char * ref_t_base_address;

extern unsigned char di_archive_data[];
extern size_t di_archive_size;


uint8_t mem[MEMSIZE];

int main(int argc,char* argv[])
{

	dj_vm * vm;
	dj_object * obj;

	// initialise memory manager
	dj_mem_init(mem, MEMSIZE);

	ref_t_base_address = (char*)mem - 42;

	// Create a new VM
	vm = dj_vm_create();

	// tell the execution engine to use the newly created VM instance
	dj_exec_setVM(vm);

	dj_named_native_handler handlers[] = {
			{ "base", &base_native_handler },
			{ "ostfriesentee", &ostfriesentee_native_handler }
		};

	int length = sizeof(handlers)/ sizeof(handlers[0]);
	dj_archive archive;
	archive.start = (dj_di_pointer)di_archive_data;
	archive.end = (dj_di_pointer)(di_archive_data + di_archive_size);

	dj_vm_loadInfusionArchive(vm, &archive, handlers, length);
	
	// pre-allocate an OutOfMemoryError object
	obj = dj_vm_createSysLibObject(vm, BASE_CDEF_java_lang_OutOfMemoryError);
	dj_mem_setPanicExceptionObject(obj);

	// start the main execution loop
	while (dj_vm_countLiveThreads(vm)>0)
	{
		dj_vm_schedule(vm);
		if (vm->currentThread!=NULL)
			if (vm->currentThread->status==THREADSTATUS_RUNNING)
				dj_exec_run(RUNSIZE);
	}

	dj_vm_schedule(vm);
	dj_mem_gc();
	dj_vm_destroy(vm);

	return 0;
}

// Ostfriesentee Resources

#include <jlib_base.h>
#include <jlib_ostfriesentee.h>

#include <types.h>
#include <vm.h>
#include <heap.h>
#include <execution.h>
#include "config.h"

#include <pointerwidth.h>

char * ref_t_base_address;
extern unsigned char di_archive_data[];
extern size_t di_archive_size;


static uint8_t mem[MEMSIZE];

void runOstfriesentee() {
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
}

// Ostfriesentee Timer
void dj_timer_init()
{

}

uint32_t dj_timer_getTimeMillis()
{
	return 0;
}

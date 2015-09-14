#ifndef SIMPLEOBJECT_HPP
#define SIMPLEOBJECT_HPP

#include <hpp/object.hpp>
#include <hpp/infusion.hpp>
#include <hpp/vm.hpp>
using namespace ostfriesentee;

namespace jlib_object {

class SimpleObject : Object {
	static constexpr uint8_t ClassId = 0;

	_OBJECT_STRUCT_SimpleObject* obj;
	dj_infusion* infusion;

public:
	SimpleObject(const SimpleObject&) = delete;
	SimpleObject& operator=(const SimpleObject&) = delete;

public:
	SimpleObject(Infusion& infusion, int32_t a, int32_t b) {
		this->infusion = infusion.getUnderlying();
		this->obj = (_OBJECT_STRUCT_SimpleObject*)create(this->infusion, ClassId);
		dj_mem_addSafePointer((void**)&this->obj);

		// parameters
		int16_t intParams[4];
		intParams[0] = (a >>  0) & 0xffff;
		intParams[1] = (a >> 16) & 0xffff;
		intParams[2] = (b >>  0) & 0xffff;
		intParams[3] = (b >> 16) & 0xffff;
		ref_t refParams[1] = { VOIDP_TO_REF(this->obj) };

		dj_global_id ctor{this->infusion, OBJECT_MDEF_void__init__int_int};
		dj_exec_callMethodFromNative(ctor, getThread(), refParams, intParams);
		dj_exec_run(100000);
	}

	~SimpleObject() {
		dj_mem_removeSafePointer((void**)&this->obj);
	}

	_OBJECT_STRUCT_SimpleObject* getUnderlying() {
		return this->obj;
	}

	int32_t getA() {
		ref_t refParams[1] = { VOIDP_TO_REF(this->obj) };
		dj_global_id method{this->infusion, OBJECT_MDEF_int_getA};
		dj_exec_callMethodFromNative(method, getThread(), refParams, nullptr);
		dj_exec_run(100000);
		return 0;
	}

};


} // namespace jlib_object


#endif // SIMPLEOBJECT_HPP

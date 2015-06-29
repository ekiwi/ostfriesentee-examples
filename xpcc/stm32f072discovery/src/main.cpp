#include <xpcc/architecture.hpp>
#include <xpcc/debug/logger.hpp>
#include "../../../ext/xpcc/examples/stm32f072_discovery/stm32f072_discovery.hpp"


#include <stdarg.h>	// for va_list
// ----------------------------------------------------------------------------
// Set the log level
#undef	XPCC_LOG_LEVEL
#define	XPCC_LOG_LEVEL xpcc::log::DEBUG


// Create an IODeviceWrapper around the Uart Peripheral we want to use
xpcc::IODeviceWrapper< Usart1, xpcc::IOBuffer::BlockIfFull > loggerDevice;

// Set all four logger streams to use the UART
xpcc::log::Logger xpcc::log::debug(loggerDevice);
xpcc::log::Logger xpcc::log::info(loggerDevice);
xpcc::log::Logger xpcc::log::warning(loggerDevice);
xpcc::log::Logger xpcc::log::error(loggerDevice);

extern "C"
void runOstfriesentee();

extern "C"
void systick_interrupt();

// Dummy Clock
struct DummyClock {
	static constexpr int Usart1 = 8 * 1000 * 1000;
};

MAIN_FUNCTION
{
	// initialize Uart1 for XPCC_LOG_
	GpioOutputA9::connect(Usart1::Tx);
	GpioInputA10::connect(Usart1::Rx, Gpio::InputType::PullUp);
	Usart1::initialize<DummyClock, 115200>();

	// enable systick timer
	xpcc::cortex::SysTickTimer::enable();
	xpcc::cortex::SysTickTimer::attachInterrupt(systick_interrupt);

	XPCC_LOG_INFO << "Ostfriesentee on STM32F0 powered by xpcc." << xpcc::endl;

	runOstfriesentee();

	return 0;
}

extern "C"
{
	#include <panic.h>
}

typedef long ssize_t;

extern "C"
ssize_t write(int /*fildes*/, const void *buf, size_t nbyte)
{
	const char* str = static_cast<const char*>(buf);
	for(int ii = 0; ii < nbyte; ++ii) {
		XPCC_LOG_INFO << str[ii];
	}

	return nbyte;
}

extern "C"
int debug_printf(const char * format, ...) {
	va_list ap;
	va_start(ap, format);

	XPCC_LOG_DEBUG.printf(format, ap);

	va_end(ap);

	return 0;
}


extern "C"
void dj_panic(int32_t panictype)
{
	switch(panictype) {
	case DJ_PANIC_OUT_OF_MEMORY:
		XPCC_LOG_ERROR << "Darjeeling panic: out of memory" << xpcc::endl;
		break;
	case DJ_PANIC_ILLEGAL_INTERNAL_STATE:
		XPCC_LOG_ERROR << "Darjeeling panic: illegal internal state" << xpcc::endl;
		break;
	case DJ_PANIC_UNIMPLEMENTED_FEATURE:
		XPCC_LOG_ERROR << "Darjeeling panic: unimplemented feature" << xpcc::endl;
		break;
	case DJ_PANIC_UNCAUGHT_EXCEPTION:
		XPCC_LOG_ERROR << "Darjeeling panic: uncaught exception" << xpcc::endl;
		break;
	case DJ_PANIC_MALFORMED_INFUSION:
		XPCC_LOG_ERROR << "Darjeeling panic: malformed infusion" << xpcc::endl;
		break;
	case DJ_PANIC_ASSERTION_FAILURE:
		XPCC_LOG_ERROR << "Darjeeling panic: Assertion failed" << xpcc::endl;
		break;
	default:
		XPCC_LOG_ERROR << "Darjeeling panic: unknown panic type" << xpcc::endl;
		break;
	}
}

static uint32_t milliseconds = 0;

// Ostfriesentee Timer
extern "C"
void systick_interrupt()
{
	++milliseconds;
}

extern "C"
uint32_t dj_timer_getTimeMillis()
{
	return milliseconds;
}

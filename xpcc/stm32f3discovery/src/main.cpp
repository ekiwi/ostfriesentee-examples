#include <xpcc/architecture.hpp>
#include <xpcc/debug/logger.hpp>
#include "../../../ext/xpcc/examples/stm32f3_discovery/stm32f3_discovery.hpp"

// ----------------------------------------------------------------------------
// Set the log level
#undef	XPCC_LOG_LEVEL
#define	XPCC_LOG_LEVEL xpcc::log::INFO


// Create an IODeviceWrapper around the Uart Peripheral we want to use
xpcc::IODeviceWrapper< Usart1, xpcc::IOBuffer::BlockIfFull > loggerDevice;

// Set all four logger streams to use the UART
xpcc::log::Logger xpcc::log::debug(loggerDevice);
xpcc::log::Logger xpcc::log::info(loggerDevice);
xpcc::log::Logger xpcc::log::warning(loggerDevice);
xpcc::log::Logger xpcc::log::error(loggerDevice);

extern "C"
void runOstfriesentee();

MAIN_FUNCTION
{
	defaultSystemClock::enable();

	// initialize Uart1 for XPCC_LOG_
	GpioOutputA9::connect(Usart1::Tx);
	GpioInputA10::connect(Usart1::Rx, Gpio::InputType::PullUp);
	Usart1::initialize<defaultSystemClock, 115200>();

	XPCC_LOG_INFO << "Ostfriesentee on STM32F3 powered by xpcc." << xpcc::endl;

	LedNorth::setOutput(xpcc::Gpio::High);
	LedNorthEast::setOutput(xpcc::Gpio::Low);
	LedEast::setOutput(xpcc::Gpio::Low);
	LedSouthEast::setOutput(xpcc::Gpio::Low);
	LedSouth::setOutput(xpcc::Gpio::Low);
	LedSouthWest::setOutput(xpcc::Gpio::Low);
	LedWest::setOutput(xpcc::Gpio::Low);
	LedNorthWest::setOutput(xpcc::Gpio::Low);

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

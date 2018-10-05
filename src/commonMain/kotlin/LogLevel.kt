package com.serebit.logkat

import com.serebit.logkat.formatting.ansiGray
import com.serebit.logkat.formatting.ansiRed
import com.serebit.logkat.formatting.ansiWhite
import com.serebit.logkat.formatting.ansiYellow
import com.serebit.logkat.writers.ConsoleWriter

/**
 * The level at which a message is output. Each LogLevel has a different level of severity, ranging from
 * [TRACE][LogLevel.TRACE] (least severe) to [ERROR][LogLevel.ERROR] (most severe). In addition, each LogLevel has an
 * assigned ANSI color transform for use with [ConsoleWriter].
 */
enum class LogLevel(internal val ansiColorTransform: (String) -> String) {
    /**
     * A log level for capturing application flow and keeping track of program execution. Not shown by default.
     */
    TRACE(String::ansiGray),
    /**
     * A log level for general debugging events. Not shown by default.
     */
    DEBUG(String::ansiGray),
    /**
     * A log level for informational purposes. Not shown by default.
     */
    INFO(String::ansiWhite),
    /**
     * A log level for events that may lead to an error. Shown by default.
     */
    WARNING(String::ansiYellow),
    /**
     * A log level for errors in the application that may be recovered from. Shown by default.
     */
    ERROR(String::ansiRed),
    /**
     * A log level for severe errors that will cause the application to crash or hang. Shown by default.
     */
    FATAL(String::ansiRed);
}

package com.serebit.logkat

import com.serebit.logkat.formatting.ansiGray
import com.serebit.logkat.formatting.ansiNormal
import com.serebit.logkat.formatting.ansiRed
import com.serebit.logkat.formatting.ansiWhite
import com.serebit.logkat.formatting.ansiYellow
import com.serebit.logkat.writers.ConsoleWriter

/**
 * The level at which a message is output. Each LogLevel has a different level of severity, ranging from
 * [TRACE][LogLevel.TRACE] (least severe) to [ERROR][LogLevel.ERROR] (most severe), along with a level that disables
 * the logger entirely ([OFF][LogLevel.OFF]). In addition, each LogLevel has an assigned ANSI color transform for use
 * with [ConsoleWriter].
 */
enum class LogLevel(internal inline val colorTransform: (String) -> String) {
    /**
     * For capturing application flow and keeping track of program execution. Not shown by default.
     */
    TRACE(String::ansiGray),
    /**
     * For general debugging events. Not shown by default.
     */
    DEBUG(String::ansiGray),
    /**
     * For informational purposes. Not shown by default.
     */
    INFO(String::ansiWhite),
    /**
     * For events that could potentially lead to an error. Shown by default.
     */
    WARNING(String::ansiYellow),
    /**
     * For errors in the application that may be recovered from. Shown by default.
     */
    ERROR(String::ansiRed),
    /**
     * For severe errors that will cause the application to crash or hang. Shown by default.
     */
    FATAL(String::ansiRed),
    /**
     * Disables all logger output.
     */
    OFF(String::ansiNormal);
}

package com.serebit.loggerkt.formatting

import com.serebit.loggerkt.LogLevel
import com.serebit.loggerkt.Logger
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

/**
 * The payload that is sent to [Logger.formatter] when formatting a message. Contains all the information needed to
 * create a log message, including the [timestamp], the [thread name][threadName], the [class name][className], the
 * [method name][methodName], the [log level][level], and the [message text][message].
 *
 * @property timestamp The timestamp at which the log function was called.
 * @property threadName The name of the thread on which the log function is executing.
 * @property className The class containing the log function's call site.
 * @property methodName The method containing the log function's call site.
 * @property level The [LogLevel] at which the formatted log message will be output.
 * @property message The message text.
 */
data class FormatterPayload(
    val timestamp: String,
    val threadName: String, val className: String, val methodName: String,
    val level: LogLevel,
    val message: String
) {
    internal companion object {
        private const val stackTraceDepth = 5

        fun generate(timestampFormatter: DateTimeFormatter, level: LogLevel, message: String): FormatterPayload {
            val thread = Thread.currentThread()
            val (className, methodName) = thread.stackTrace[stackTraceDepth].let {
                it.className.split(".").last() to it.methodName
            }
            return FormatterPayload(
                OffsetDateTime.now().format(timestampFormatter),
                thread.name, className, methodName,
                level,
                message
            )
        }
    }
}

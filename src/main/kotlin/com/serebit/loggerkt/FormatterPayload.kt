package com.serebit.loggerkt

import java.time.OffsetDateTime

/**
 * The payload that is sent to [Logger.formatter] when creating a new [LogMessage]. Contains all the information
 * needed to create a log message, including the [time], the [thread name][threadName], the [class name][className],
 * the [method name][methodName], the [log level][level], and the [message text][message].
 *
 * @property time The time at which the log function was called.
 * @property threadName The name of the thread on which the log function is executing.
 * @property className The class containing the log function's call site.
 * @property methodName The method containing the log function's call site.
 * @property level The [LogLevel] at which the formatted log message will be output.
 * @property message The message text.
 */
data class FormatterPayload(
    val time: OffsetDateTime,
    val threadName: String, val className: String, val methodName: String,
    val level: LogLevel,
    val message: String
) {
    internal companion object {
        private const val stackTraceDepth = 4

        fun generate(level: LogLevel, message: String): FormatterPayload {
            val thread = Thread.currentThread()
            val (className, methodName) = thread.stackTrace[stackTraceDepth].let {
                it.className.split(".").last() to it.methodName
            }
            return FormatterPayload(
                OffsetDateTime.now(),
                thread.name, className, methodName,
                level,
                message
            )
        }
    }
}

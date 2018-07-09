package com.serebit.loggerkt.formatting

import com.serebit.loggerkt.LogLevel

internal actual object FormatterPayloadGenerator {
    private const val stackTraceDepth = 5

    actual fun generate(timestampGenerator: TimestampGenerator, level: LogLevel, message: String): FormatterPayload {
        val thread = Thread.currentThread()
        val (className, methodName) = thread.stackTrace[stackTraceDepth].let {
            it.className.split(".").last() to it.methodName
        }
        return FormatterPayload(
            timestampGenerator.now(),
            thread.name, className, methodName,
            level,
            message
        )
    }
}

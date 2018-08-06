package com.serebit.loggerkt.formatting

import com.serebit.loggerkt.LogLevel

data class FormatterPayload(
    val timestamp: String,
    val thread: String,
    val level: LogLevel,
    val message: String
) {
    companion object {
        internal fun generate(timestampGenerator: TimestampGenerator, level: LogLevel, message: String) =
            FormatterPayload(
                timestampGenerator.now(),
                ThreadProvider.currentThreadName,
                level,
                message
            )
    }
}

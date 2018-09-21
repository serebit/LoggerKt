package com.serebit.loggerkt.formatting

import com.serebit.loggerkt.LogLevel

data class FormatterPayload(
    val timestamp: String,
    val thread: String,
    val level: LogLevel,
    val message: String
) {
    internal constructor(timestampGenerator: TimestampGenerator, level: LogLevel, message: String) :
            this(timestampGenerator.now(), ThreadProvider.currentThreadName, level, message)
}

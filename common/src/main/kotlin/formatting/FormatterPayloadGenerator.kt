package com.serebit.loggerkt.formatting

import com.serebit.loggerkt.LogLevel

internal expect object FormatterPayloadGenerator {
    fun generate(timestampGenerator: TimestampGenerator, level: LogLevel, message: String): FormatterPayload
}

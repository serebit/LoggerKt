package com.serebit.logkat.formatting

import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

internal actual class TimestampGenerator actual constructor(pattern: String) {
    private var formatter = DateTimeFormatter.ofPattern(pattern)

    actual var pattern
        get() = formatter.toString()
        set(value) {
            formatter = DateTimeFormatter.ofPattern(value)
        }

    actual fun now(): String = formatter.format(OffsetDateTime.now())
}

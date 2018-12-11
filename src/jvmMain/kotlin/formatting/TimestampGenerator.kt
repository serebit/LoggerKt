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

    actual fun generate(): String = formatter.format(OffsetDateTime.now())

    actual companion object {
        actual val DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss"
    }
}

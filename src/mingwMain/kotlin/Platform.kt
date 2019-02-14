package com.serebit.logkat.platform

internal actual object Platform {
    actual val supportsAnsiColors = false
    actual val lineSeparator = "\r\n"
}

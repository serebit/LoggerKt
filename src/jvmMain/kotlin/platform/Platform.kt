package com.serebit.logkat.platform

internal actual object Platform {
    actual val supportsAnsiColors = !System.getProperty("os.name").startsWith("Windows")
    actual val lineSeparator = System.lineSeparator()
}

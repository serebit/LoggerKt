package com.serebit.logkat.platform

internal expect object Platform {
    val supportsAnsiColors: Boolean
    val lineSeparator: String
}

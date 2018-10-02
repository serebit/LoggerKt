package com.serebit.logkat.platform

internal expect object Platform {
    val classpath: String
    val supportsAnsiColors: Boolean
}

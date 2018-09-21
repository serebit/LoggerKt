package com.serebit.loggerkt.platform

internal expect object Platform {
    val classpath: String
    val supportsAnsiColors: Boolean
}

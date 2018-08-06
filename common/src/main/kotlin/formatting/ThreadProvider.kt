package com.serebit.loggerkt.formatting

internal expect object ThreadProvider {
    val currentThreadName: String
}

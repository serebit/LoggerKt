package com.serebit.logkat.formatting

internal expect object ThreadProvider {
    val currentThreadName: String
}

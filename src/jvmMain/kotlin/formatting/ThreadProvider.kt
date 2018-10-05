package com.serebit.logkat.formatting

internal actual object ThreadProvider {
    actual val currentThreadName: String = Thread.currentThread().name
}

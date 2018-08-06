package com.serebit.loggerkt.formatting

internal actual object ThreadProvider {
    actual val currentThreadName: String = Thread.currentThread().name
}

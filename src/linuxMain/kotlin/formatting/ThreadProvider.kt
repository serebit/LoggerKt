package com.serebit.logkat.formatting

import platform.posix.pthread_self

internal actual object ThreadProvider {
    actual val currentThreadName: String = pthread_self().toString()
}

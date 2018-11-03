package com.serebit.logkat.platform

import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.toKString
import platform.posix.dirname
import platform.posix.readlink

internal actual object Platform {
    actual val classpath: String = memScoped {
        val buffer = allocArray<ByteVar>(256)
        readlink("/proc/self/exe", buffer, 256)
        dirname(buffer)!!.toKString()
    }
    actual val supportsAnsiColors: Boolean
        get() = true
}

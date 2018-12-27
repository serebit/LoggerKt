package com.serebit.logkat.platform

import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.toKString
import platform.posix.PATH_MAX
import platform.posix.dirname
import platform.posix.readlink

internal actual object Platform {
    actual val classpath: String = memScoped {
        val buffer = allocArray<ByteVar>(PATH_MAX)
        readlink("/proc/self/exe", buffer, PATH_MAX)
        dirname(buffer)!!.toKString()
    }
    actual val supportsAnsiColors = true
    actual val lineSeparator = "\n"
    actual val pathSeparator = '/'
}

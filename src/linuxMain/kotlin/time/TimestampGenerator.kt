package com.serebit.logkat.time

import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.alloc
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.cinterop.toKString
import platform.posix.localtime
import platform.posix.strftime
import platform.posix.time
import platform.posix.time_tVar

internal actual class TimestampGenerator actual constructor(actual var pattern: String) {
    actual fun generate(): String = memScoped {
        val timePointer = alloc<time_tVar>()
        time(timePointer.ptr)
        val timeInfo = localtime(timePointer.ptr)
        val buffer = allocArray<ByteVar>(26)

        strftime(buffer, 26, pattern, timeInfo)

        buffer.toKString()
    }

    actual companion object {
        actual val DEFAULT_PATTERN: String = "%Y-%m-%d %H:%M:%S"
    }
}

package com.serebit.logkat.formatting

import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.toCPointer
import kotlinx.cinterop.toKString
import platform.posix.localtime
import platform.posix.strftime
import platform.posix.time

internal actual class TimestampGenerator actual constructor(actual var pattern: String) {
    actual fun now(): String {
        val buffer = memScoped { allocArray<ByteVar>(BUFFER_SIZE) }
        val rawTime = 0L
        time(rawTime.toCPointer())
        val timeInfo = localtime(rawTime.toCPointer())

        strftime(buffer, BUFFER_SIZE.toULong(), pattern, timeInfo)

        return buffer.toKString()
    }

    actual companion object {
        actual val DEFAULT_PATTERN: String = "%Y-%m-%d %H:%M:%S"
        private const val BUFFER_SIZE = 26
    }
}

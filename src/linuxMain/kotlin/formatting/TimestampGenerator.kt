package com.serebit.logkat.formatting

import kotlinx.cinterop.toCPointer
import kotlinx.cinterop.toKString
import platform.posix.asctime
import platform.posix.localtime
import platform.posix.time

internal actual class TimestampGenerator actual constructor(pattern: String) {
    actual var pattern: String = ""

    actual fun now(): String {
        val rawTime = 0L
        time(rawTime.toCPointer())
        val timeInfo = localtime(rawTime.toCPointer())

        return asctime(timeInfo)!!.toKString()
    }
}

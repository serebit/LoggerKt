package com.serebit.logkat.time

import kotlinx.cinterop.alloc
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.pointed
import kotlinx.cinterop.ptr
import platform.posix.localtime
import platform.posix.time
import platform.posix.time_tVar

internal actual fun now(): DateTime = memScoped {
    val timePointer = alloc<time_tVar>()
    time(timePointer.ptr)
    val timeInfo = localtime(timePointer.ptr)!!.pointed
    return DateTime(
        timeInfo.tm_year,
        timeInfo.tm_mon,
        timeInfo.tm_yday,
        timeInfo.tm_mday,
        timeInfo.tm_wday,
        timeInfo.tm_hour,
        timeInfo.tm_min,
        timeInfo.tm_sec
    )
}

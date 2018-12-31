package com.serebit.logkat.time

import kotlinx.cinterop.alloc
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.pointed
import kotlinx.cinterop.ptr
import platform.posix.CLOCK_REALTIME
import platform.posix.clock_gettime
import platform.posix.localtime
import platform.posix.time
import platform.posix.time_tVar
import platform.posix.timespec

internal actual fun now(): DateTime = memScoped {
    val timePointer = alloc<time_tVar>()
    time(timePointer.ptr)
    val timeInfo = localtime(timePointer.ptr)!!.pointed
    val timeSpec = alloc<timespec>()
    clock_gettime(CLOCK_REALTIME, timeSpec.ptr)
    return DateTime(
        timeInfo.tm_year,
        timeInfo.tm_mon,
        timeInfo.tm_yday,
        timeInfo.tm_mday,
        timeInfo.tm_wday,
        timeInfo.tm_hour,
        timeInfo.tm_min,
        timeInfo.tm_sec,
        timeSpec.tv_nsec.toInt()
    )
}

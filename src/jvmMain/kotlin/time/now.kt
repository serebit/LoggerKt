package com.serebit.logkat.time

import java.time.LocalDateTime

internal actual fun now(): DateTime {
    val platformTime = LocalDateTime.now()
    return DateTime(
        platformTime.year,
        platformTime.monthValue,
        platformTime.dayOfYear,
        platformTime.dayOfMonth,
        platformTime.dayOfWeek.value,
        platformTime.hour,
        platformTime.minute,
        platformTime.second,
        platformTime.nano
    )
}

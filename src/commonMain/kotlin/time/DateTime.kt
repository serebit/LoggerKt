package com.serebit.logkat.time

import kotlin.math.floor
import kotlin.math.log10
import kotlin.math.roundToInt

class DateTime(
    year: Int, month: Int,
    dayOfYear: Int, dayOfMonth: Int, dayOfWeek: Int,
    hour: Int, minute: Int, second: Int, nanosecond: Int
) {
    /**
     * The year as a formatted String, including century (such as 1999, 2018, or 1492)
     */
    val year = year.coerceAtLeast(0).toString()
    /**
     * The month as a formatted String, ranging from 01 to 12.
     */
    val month = month.coerceIn(1..12).pad(1)
    /**
     * The name of the month, with 1 corresponding to January and 12 to December.
     */
    val monthName = MONTH_NAMES[month.coerceIn(1..12) - 1]
    /**
     * The name of the day of the week, starting with Monday and ending with Sunday.
     */
    val weekDay = WEEK_DAY_NAMES[dayOfWeek.coerceIn(1..7) - 1]
    /**
     * The day of the year as a formatted String, ranging from 001 to 366 (leap day).
     */
    val yearDay = dayOfYear.coerceIn(1..366).pad(2)
    /**
     * The day of the month as a formatted String, ranging from 01 to 31.
     */
    val day = dayOfMonth.coerceIn(1..31).pad(1)
    /**
     * The hour of the day as a formatted String, ranging from 00 to 23.
     */
    val hour = hour.coerceIn(0..23).pad(1)
    /**
     * The minute of the hour as a formatted String, ranging from 00 to 59.
     */
    val minute = minute.coerceIn(0..59).pad(1)
    /**
     * The second of the minute as a formatted String, ranging from 00 to 59.
     */
    val second = second.coerceIn(0..59).pad(1)
    /**
     * The millisecond of the second as a formatted String, ranging from 000 to 999.
     */
    val millisecond = floor(nanosecond / 1.0e6).roundToInt().coerceIn(0 until 1000).pad(2)

    private fun Int.pad(maxZeroes: Int): String {
        val log = if (this > 0) floor(log10(toDouble())).roundToInt() else 0
        val prependingZeroes = maxZeroes - log
        return "${"0".repeat(prependingZeroes)}$this"
    }

    companion object {
        private val WEEK_DAY_NAMES =
            listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
        private val MONTH_NAMES = listOf(
            "January", "February", "March", "April", "May", "June", "July", "August", "September", "October",
            "November", "December"
        )
    }
}

internal expect fun now(): DateTime

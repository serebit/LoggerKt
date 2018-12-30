package com.serebit.logkat.time

import kotlin.math.floor
import kotlin.math.log10
import kotlin.math.roundToInt

class DateTime(
    year: Int,
    month: Int,
    dayOfYear: Int,
    dayOfMonth: Int,
    dayOfWeek: Int,
    hour: Int, minute: Int, second: Int
) {
    val year = year.coerceAtLeast(0).toString()
    val month = month.coerceIn(1..12).pad()
    val monthName = monthNames[month.coerceIn(1..12) - 1]
    val weekDay = weekDayNames[dayOfWeek.coerceIn(1..7) - 1]
    val yearDay = dayOfYear.coerceIn(1..366).pad()
    val day = dayOfMonth.coerceIn(1..31).pad()
    val hour = hour.coerceIn(0..23).pad()
    val minute = minute.coerceIn(0..59).pad()
    val second = second.coerceIn(0..59).pad()

    private fun Int.pad(): String {
        val prependingZeroes = floor(log10(this.toFloat())).roundToInt()
        return "${"0".repeat(prependingZeroes)}this"
    }

    companion object {
        private val weekDayNames = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
        private val monthNames = listOf(
            "January", "February", "March", "April", "May", "June", "July", "August", "September", "October",
            "November", "December"
        )
    }
}

internal expect fun now(): DateTime

package com.serebit.logkat.time

class DateTime(
    year: Int,
    month: Int,
    dayOfYear: Int,
    dayOfMonth: Int,
    dayOfWeek: Int,
    hour: Int,
    minute: Int,
    second: Int
) {
    val year = year.coerceAtLeast(0).toString()
    val month = month.coerceIn(1..12).pad()
    val monthName = monthNames[month.coerceIn(1..12) - 1]
    val day = dayOfMonth.coerceIn(1..31).pad()
    val hour = hour.coerceIn(0..23).pad()
    val minute = minute.coerceIn(0..59).pad()
    val second = second.coerceIn(0..59).pad()

    private fun Int.pad() = if (this < 10) toString() else "0$this"

    companion object {
        val monthNames = listOf(
            "January", "February", "March", "April", "May", "June", "July", "August", "September", "October",
            "November", "December"
        )
    }
}

internal expect fun now(): DateTime

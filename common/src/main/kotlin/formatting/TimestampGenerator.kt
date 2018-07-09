package com.serebit.loggerkt.formatting

internal expect class TimestampGenerator(pattern: String) {
    var pattern: String

    fun now(): String
}

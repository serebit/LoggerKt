package com.serebit.logkat.time

internal expect class TimestampGenerator(pattern: String = DEFAULT_PATTERN) {
    var pattern: String

    fun generate(): String

    companion object {
        val DEFAULT_PATTERN: String
    }
}

package com.serebit.logkat.formatting

internal expect class TimestampGenerator(pattern: String) {
    var pattern: String

    fun generate(): String

    companion object {
        val DEFAULT_PATTERN: String
    }
}

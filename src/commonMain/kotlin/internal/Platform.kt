package com.serebit.logkat.internal

internal expect class File(path: String) {
    val absolutePath: String

    fun appendText(text: String)

    fun readText(): String

    fun delete(): Boolean

    companion object {
        val classpath: String
        val pathSeparator: Char
        fun createTempFile(prefix: String): File
    }
}

internal expect object Platform {
    val supportsAnsiColors: Boolean
    val lineSeparator: String
}

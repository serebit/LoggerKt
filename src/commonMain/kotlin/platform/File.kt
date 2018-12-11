package com.serebit.logkat.platform

internal expect class File(path: String) {
    val absolutePath: String

    fun appendText(text: String)

    fun readText(): String

    fun delete(): Boolean
}

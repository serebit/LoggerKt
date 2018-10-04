package com.serebit.logkat.platform

expect class File(path: String) {
    fun appendText(text: String)

    fun delete(): Boolean
}

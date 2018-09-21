package com.serebit.loggerkt.platform

expect class File(path: String) {
    fun appendText(text: String)

    fun delete(): Boolean
}

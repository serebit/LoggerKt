package com.serebit.logkat.platform

import java.io.File as JvmFile

actual class File actual constructor(path: String) {
    private val file = JvmFile(path)
    actual val absolutePath: String get() = file.absolutePath

    actual fun appendText(text: String) = file.appendText(text)

    actual fun readText(): String = file.readText()

    actual fun delete() = file.delete()
}

package com.serebit.loggerkt.platform

import java.io.File as JvmFile

actual class File actual constructor(path: String) {
    private val file = JvmFile(path)

    actual fun appendText(text: String) = file.appendText(text)

    actual fun delete() = file.delete()
}

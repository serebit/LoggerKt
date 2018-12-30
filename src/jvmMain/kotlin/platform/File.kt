package com.serebit.logkat.platform

import java.io.File as JvmFile

internal actual class File actual constructor(path: String) {
    private val realPath = if (path.startsWith("/")) {
        path
    } else {
        "$classpath/$path"
    }

    private val file = JvmFile(realPath)
    actual val absolutePath: String get() = file.absolutePath

    actual fun appendText(text: String) = file.appendText(text)

    actual fun readText(): String = file.readText()

    actual fun delete() = file.delete()

    actual companion object {
        actual val classpath: String = JvmFile(this::class.java.protectionDomain.codeSource.location.path).parent
        actual val pathSeparator = JvmFile.pathSeparatorChar
        actual fun createTempFile(prefix: String): File = File(kotlin.io.createTempFile(prefix).absolutePath)
    }
}

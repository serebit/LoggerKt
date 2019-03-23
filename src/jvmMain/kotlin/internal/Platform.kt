package com.serebit.logkat.internal

internal actual class File actual constructor(path: String) {
    private val realPath = if (path.startsWith("/")) {
        path
    } else {
        "$classpath/$path"
    }

    private val file = java.io.File(realPath)
    actual val absolutePath: String get() = file.absolutePath

    actual fun appendText(text: String) = file.appendText(text)

    actual fun readText(): String = file.readText()

    actual fun delete() = file.delete()

    actual companion object {
        actual val classpath: String = java.io.File(this::class.java.protectionDomain.codeSource.location.path).parent
        actual val pathSeparator = java.io.File.pathSeparatorChar
        actual fun createTempFile(prefix: String): File = File(kotlin.io.createTempFile(prefix).absolutePath)
    }
}

internal actual object Platform {
    actual val supportsAnsiColors = !System.getProperty("os.name").startsWith("Windows")
    actual val lineSeparator = System.lineSeparator()
}

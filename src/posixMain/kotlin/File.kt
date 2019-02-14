package com.serebit.logkat.platform

import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.toKString
import platform.posix.*
import kotlin.math.absoluteValue
import kotlin.random.Random

internal actual class File actual constructor(private val path: String) {
    actual val absolutePath: String = memScoped {
        val buffer = allocArray<ByteVar>(PATH_MAX)
        realpath(path, buffer)
        buffer.toKString()
    }

    actual fun appendText(text: String) {
        val workingFile = fopen(path, "a")!!
        fprintf(workingFile, "%s", text)
        fclose(workingFile)
    }

    @ExperimentalUnsignedTypes
    actual fun readText(): String {
        val file = fopen(path, "rb")!!
        fseek(file, 0, SEEK_END)
        val fileLength = ftell(file)
        fseek(file, 0, SEEK_SET)
        return memScoped {
            val buffer = allocArray<ByteVar>(fileLength + 1)
            fread(buffer, fileLength.toULong(), 1, file)
            fclose(file)
            buffer.toKString()
        }
    }

    actual fun delete(): Boolean = remove(path) == 0

    actual companion object {
        actual val classpath: String = memScoped {
            val buffer = allocArray<ByteVar>(PATH_MAX)
            readlink("/proc/self/exe", buffer, PATH_MAX)
            dirname(buffer)!!.toKString()
        }
        actual val pathSeparator = '/'
        actual fun createTempFile(prefix: String): File =
            File("/tmp/$prefix${Random.nextLong().absoluteValue}.tmp")
    }
}

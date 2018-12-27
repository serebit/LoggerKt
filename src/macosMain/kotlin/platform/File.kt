package com.serebit.logkat.platform

import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.toKString
import platform.posix.PATH_MAX
import platform.posix.SEEK_END
import platform.posix.SEEK_SET
import platform.posix.fclose
import platform.posix.fopen
import platform.posix.fprintf
import platform.posix.fread
import platform.posix.fseek
import platform.posix.ftell
import platform.posix.realpath
import platform.posix.remove
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
        actual fun createTempFile(prefix: String): File = File("$prefix${Random.nextLong().absoluteValue}.tmp")
    }
}

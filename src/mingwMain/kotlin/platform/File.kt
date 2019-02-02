package com.serebit.logkat.platform

import kotlinx.cinterop.*
import platform.posix.*
import platform.windows.GetModuleFileNameA
import platform.windows.GetTempPathA
import kotlin.math.absoluteValue
import kotlin.random.Random

internal actual class File actual constructor(private val path: String) {
    actual val absolutePath = memScoped {
        val buffer = allocArray<ByteVar>(PATH_MAX)
        _fullpath(buffer, path, PATH_MAX)
        buffer.toKString()
    }

    actual fun appendText(text: String) {
        val workingFile = fopen(path, "a")!!
        fprintf(workingFile, "%s", text)
        fclose(workingFile)
    }

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

    actual fun delete() = remove(path) == 0

    actual companion object {
        actual val classpath = memScoped {
            val buffer = allocArray<ByteVar>(PATH_MAX)
            GetModuleFileNameA(null, buffer, PATH_MAX.toUInt())
            dirname(buffer)!!.toKString()
        }
        actual val pathSeparator = '\\'

        actual fun createTempFile(prefix: String): File = memScoped {
            val tempPath = allocArray<ByteVar>(PATH_MAX)
            GetTempPathA(PATH_MAX, tempPath)
            File("${dirname(tempPath)!!.toKString()}\\$prefix${Random.nextLong().absoluteValue}.tmp")
        }
    }
}

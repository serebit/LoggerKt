package com.serebit.logkat.platform

import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.memScoped
import platform.posix.fgets
import platform.posix.fopen
import platform.posix.fprintf
import platform.posix.remove

actual class File actual constructor(private val path: String) {
    actual fun appendText(text: String) = memScoped {
        val originalFile = fopen(path, "r")
        val workingFile = fopen(path, "a")!!
        val buffer = allocArray<ByteVar>(BUFFER_SIZE)

        while(fgets(buffer, BUFFER_SIZE, originalFile) != null) {
            fprintf(workingFile, "%s", buffer)
        }
    }
    actual fun delete(): Boolean = remove(path) == 0

    companion object {
        private const val BUFFER_SIZE = 256
    }
}

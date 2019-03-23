package com.serebit.logkat.writers

import com.serebit.logkat.LogLevel
import com.serebit.logkat.internal.File

/**
 * An implementation of [MessageWriter] that outputs to a file at the given path. Can be set to either append to the
 * file at the given path, or overwrite it entirely.
 *
 * @param path The path at which the file will reside. If the path starts with a slash, the file will be created from
 * the given path itself. Otherwise, the file will be created at a location relative to the parent folder of the
 * classpath.
 * @param overwrite Whether or not the file at the specified path will be overwritten. Defaults to false.
 */
class FileWriter(path: String, overwrite: Boolean = false) : MessageWriter {
    private val file = File(path).also { if (overwrite) it.delete() }

    /**
     * Appends the given [message] to the file.
     */
    override fun write(message: String, level: LogLevel) = file.appendText("$message\n")
}

package com.serebit.loggerkt.writers

import java.io.File

/**
 * An implementation of [LogWriter] that outputs to a file at the given path. Can be set to either append to the
 * file at the given path, or overwrite it entirely.
 *
 * @constructor Creates an instance writing to a file at the given path.
 * @param path The path at which the file will reside. If the path starts with a slash, the file will be created from
 * the given path itself. Otherwise, the file will be created at a location relative to the parent folder of the
 * classpath.
 * @param overwrite Whether or not the file at the specified path will be overwritten. Defaults to true.
 */
class FileWriter(path: String, overwrite: Boolean = true) : LogWriter {
    private val file = if (path.startsWith("/")) {
        File(path)
    } else {
        File("${this::class.java.protectionDomain.codeSource.location.path}/../$path")
    }.also { if (overwrite) it.delete() }

    /**
     * Appends the given [message] to the file.
     */
    override fun write(message: String) = file.appendText("$message\n")
}

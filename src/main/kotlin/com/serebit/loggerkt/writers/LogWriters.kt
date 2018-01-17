@file:JvmName("LogWriters")

package com.serebit.loggerkt.writers

import com.serebit.loggerkt.LogLevel
import java.io.File

interface LogWriter {
    fun log(level: LogLevel, message: String)
}

/**
 * An implementation of [LogWriter] that outputs to a file. This output is not color-coded.
 *
 * @param path The path to the log file.
 */
class FileWriter(path: String, overwrite: Boolean = true) : LogWriter {
    private val file = if (path.startsWith("/")) {
        File(path)
    } else {
        File(this::class.java.protectionDomain.codeSource.location.toURI().path + "/" + path)
    }.also { if (overwrite) it.delete() }

    override fun log(level: LogLevel, message: String) = file.appendText("$message\n")
}

/**
 * An implementation of [LogWriter] that outputs to the console. This writer's output can be color-coded.
 *
 * @property useAnsiColors Defines whether or not the writer will output color-coded text to the console.
 */
class ConsoleWriter(private val useAnsiColors: Boolean = true) : LogWriter {
    override fun log(level: LogLevel, message: String) = if (useAnsiColors) {
        println(message.let(level.ansiColorTransform))
    } else {
        println(message)
    }
}

/**
 * An implementation of [LogWriter] that contains multiple LogWriters. This writer passes the log messages through to
 * its internal writers, allowing for multiple log outputs.
 *
 * @property writers The writers that will log the messages passed to this writer.
 */
class MultiWriter(private vararg val writers: LogWriter) : LogWriter {
    override fun log(level: LogLevel, message: String) = writers.forEach { it.log(level, message) }
}
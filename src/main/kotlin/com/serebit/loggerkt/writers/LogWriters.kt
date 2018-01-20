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
 * @param path The path at which the file will reside.
 * @param overwrite Whether or not the file at the specified path will be overwritten. Defaults to true.
 */
class FileWriter(path: String, overwrite: Boolean = true) : LogWriter {
    private val file = if (path.startsWith("/")) {
        File(path)
    } else {
        File("${this::class.java.protectionDomain.codeSource.location.toURI().path}/$path")
    }.also { if (overwrite) it.delete() }

    override fun log(level: LogLevel, message: String) = file.appendText("$message\n")
}

/**
 * An implementation of [LogWriter] that outputs to the console. This writer's output is color coded using ANSI
 * escape codes if the property [useAnsiColors] is set to true and the OS is not Windows.
 */
class ConsoleWriter(useAnsiColors: Boolean = true) : LogWriter {
    private val useAnsiColors = if (System.getProperty("os.name").startsWith("Windows")) false else useAnsiColors

    override fun log(level: LogLevel, message: String) = if (useAnsiColors) {
        println(message.let(level.ansiColorTransform))
    } else {
        println(message)
    }
}

/**
 * An implementation of [LogWriter] that contains multiple LogWriters. This writer passes the log messages through to
 * its internal [writers], allowing for multiple log outputs.
 */
class MultiWriter(private vararg val writers: LogWriter) : LogWriter {
    override fun log(level: LogLevel, message: String) = writers.forEach { it.log(level, message) }
}
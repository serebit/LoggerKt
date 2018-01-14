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
class FileWriter(path: String) : LogWriter {
    private val file = if (path.startsWith("/")) {
        File(path)
    } else {
        File(this::class.java.protectionDomain.codeSource.location.toURI().path + "/" + path)
    }.also { it.writeText("") }

    override fun log(level: LogLevel, message: String) = file.appendText("$message\n")
}

/**
 * An implementation of [LogWriter] that outputs to the console. The output of this LogWriter is color-coded based
 * on the log level, using ANSI color codes.
 */
class ConsoleWriter(private val useAnsiColors: Boolean = true) : LogWriter {
    override fun log(level: LogLevel, message: String) {
        println(message.let {
            if (useAnsiColors) it.let(level.ansiColorTransform)
        })
    }
}
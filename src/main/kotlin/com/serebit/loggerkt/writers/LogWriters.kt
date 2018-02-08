@file:JvmName("LogWriters")

package com.serebit.loggerkt.writers

import com.serebit.loggerkt.LeveledLogMessage
import com.serebit.loggerkt.LogMessage
import java.io.File
import java.io.PrintStream

interface LogWriter {
    fun log(message: LogMessage)
}

/**
 * An implementation of [LogWriter] that outputs to a [file] at the given path.
 *
 * @param path The path at which the file will reside.
 * @param overwrite Whether or not the file at the specified path will be overwritten. Defaults to true.
 */
class FileWriter(path: String, overwrite: Boolean = true) : LogWriter {
    private val file = if (path.startsWith("/")) {
        File(path)
    } else {
        File("${this::class.java.protectionDomain.codeSource.location.path}/../$path")
    }.also { if (overwrite) it.delete() }

    override fun log(message: LogMessage) = file.appendText("$message\n")
}

/**
 * An implementation of [LogWriter] that outputs to the console. This writer's output is color coded using ANSI
 * escape codes if the property [useAnsiColors] is set to true and the OS is not Windows.
 */
class ConsoleWriter(useAnsiColors: Boolean = true) : LogWriter {
    private val useAnsiColors = if (System.getProperty("os.name").startsWith("Windows")) false else useAnsiColors

    override fun log(message: LogMessage) = if (useAnsiColors && message is LeveledLogMessage) {
        println(message.withAnsiColorTransform)
    } else {
        println(message)
    }
}

/**
 * An implementation of [LogWriter] that outputs to the given [stream]. This writer's output is not color-coded,
 * as that property is exclusive to [ConsoleWriter].
 */
class PrintStreamWriter(private val stream: PrintStream) : LogWriter {
    override fun log(message: LogMessage) {
        stream.println(message.text)
    }
}

/**
 * An implementation of [LogWriter] that contains multiple LogWriters. This writer passes the log messages through to
 * its internal [writers], allowing for multiple log outputs.
 */
class MultiWriter(private vararg val writers: LogWriter) : LogWriter {
    override fun log(message: LogMessage) = writers.forEach {
        it.log(message)
    }
}
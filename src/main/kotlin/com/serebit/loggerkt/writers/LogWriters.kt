@file:JvmName("LogWriters")

package com.serebit.loggerkt.writers

import com.serebit.loggerkt.LeveledLogMessage
import com.serebit.loggerkt.LogMessage
import java.io.File
import java.io.PrintStream

/**
 * The interface which all LogWriters inherit.
 */
interface LogWriter {
    /**
     * Logs the given [message].
     */
    fun log(message: LogMessage)
}

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
    override fun log(message: LogMessage) = file.appendText("$message\n")
}

/**
 * An implementation of [LogWriter] that outputs to the console. This writer's output is color coded using ANSI
 * escape codes by default, unless the operating system is Windows.
 *
 * @constructor Creates a new instance, using ANSI color transforms if specified.
 * @param shouldUseAnsiColors Whether or not the writer should use ANSI color transforms. Defaults to true.
 */
class ConsoleWriter(shouldUseAnsiColors: Boolean = true) : LogWriter {
    private val useAnsiColors = if (System.getProperty("os.name").startsWith("Windows")) false else shouldUseAnsiColors

    /**
     * Prints the given [message] to the console, applying the proper ANSI color transform if [useAnsiColors] is set to
     * true and the given [message] is an instance of [LeveledLogMessage].
     */
    override fun log(message: LogMessage) = if (useAnsiColors && message is LeveledLogMessage) {
        println(message.withAnsiColorTransform)
    } else {
        println(message)
    }
}

/**
 * An implementation of [LogWriter] that outputs to the given [stream]. This writer's output is not color-coded,
 * as that property is exclusive to [ConsoleWriter].
 *
 * @constructor Creates a new instance with the specified [stream].
 * @param stream The output stream to be printed to.
 */
class PrintStreamWriter(private val stream: PrintStream) : LogWriter {
    /**
     * Prints the given [message] to the specified [stream].
     */
    override fun log(message: LogMessage) {
        stream.println(message.text)
    }
}

/**
 * An implementation of [LogWriter] that contains multiple LogWriters. This writer passes the log messages through to
 * its internal [writers], allowing for multiple log outputs.
 *
 * @constructor The default constructor.
 */
class MultiWriter(private vararg val writers: LogWriter) : LogWriter {
    override fun log(message: LogMessage) = writers.forEach {
        it.log(message)
    }
}
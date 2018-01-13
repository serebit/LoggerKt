@file:JvmName("LogWriters")

package com.serebit.loggerkt

import java.io.File

interface LogWriter {
    fun log(message: String)
}

/**
 * An implementation of [LogWriter] that outputs to a file. This output is not color-coded.
 *
 * @param path The path to the log file.
 */
class FileWriter(path: String) : LogWriter {
    private val printWriter = if (path.startsWith("/")) {
        File(path)
    } else {
        File(this::class.java.protectionDomain.codeSource.location.toURI().path + "/" + path)
    }.printWriter()

    override fun log(message: String) = printWriter.use {
        it.println(message)
    }
}

/**
 * An implementation of [LogWriter] that outputs to the console. The output of this LogWriter is color-coded based
 * on the log level, using ANSI color codes.
 */
class ConsoleWriter : LogWriter {
    override fun log(message: String) = println(message)
}
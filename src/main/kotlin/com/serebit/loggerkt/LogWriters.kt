@file:JvmName("LogWriters")

package com.serebit.loggerkt

import java.io.File

interface LogWriter {
    fun log(message: String)
}

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

class ConsoleWriter : LogWriter {
    override fun log(message: String) = println(message)
}
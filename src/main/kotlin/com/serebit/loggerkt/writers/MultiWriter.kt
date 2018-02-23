package com.serebit.loggerkt.writers

import com.serebit.loggerkt.LogMessage
import java.io.PrintStream

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

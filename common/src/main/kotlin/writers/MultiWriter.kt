package com.serebit.loggerkt.writers

/**
 * An implementation of [LogWriter] that contains multiple LogWriters. This writer passes the log messages through to
 * its internal writers, allowing for multiple log outputs.
 *
 * @constructor The default constructor.
 */
class MultiWriter(private vararg val writers: LogWriter) : LogWriter {
    override fun write(message: String) = writers.forEach {
        it.write(message)
    }
}

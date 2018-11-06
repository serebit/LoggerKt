package com.serebit.logkat.writers

import com.serebit.logkat.LogLevel

/**
 * An implementation of [MessageWriter] that contains multiple LogWriters. This writer passes the log messages through to
 * its internal writers, allowing for multiple log outputs.
 */
class MultiWriter(private vararg val writers: MessageWriter) : MessageWriter {
    override fun write(message: String, level: LogLevel) = writers.forEach { it.write(message, level) }
}

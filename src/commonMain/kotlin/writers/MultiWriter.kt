package com.serebit.logkat.writers

import com.serebit.logkat.LogLevel

/**
 * An implementation of [MessageWriter] that contains multiple LogWriters. This writer passes
 * the log messages through to its internal writers, allowing for multiple log outputs.
 */
class MultiWriter(private vararg val writers: MessageWriter) : MessageWriter {
    /** Calls each the [MessageWriter.write] function of each of this MultiWriter's [writers]. */
    override fun write(message: String, level: LogLevel): Unit = writers.forEach { it.write(message, level) }
}

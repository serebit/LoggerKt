package com.serebit.logkat.writers

import com.serebit.logkat.LogLevel
import com.serebit.logkat.internal.Platform

/**
 * An implementation of [MessageWriter] that writes to a string buffer.
 * The chronological order of log entries is not guaranteed.
 */
class BufferWriter : MessageWriter {
    private val buffer = StringBuilder()

    override fun write(message: String, level: LogLevel) {
        buffer.append("$message${Platform.lineSeparator}")
    }

    /** Reads from the buffer to a String. */
    fun read(): String = buffer.toString()

    /** Clears the buffer. */
    fun clear(): Unit {
        buffer.clear()
    }

}

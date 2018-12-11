package com.serebit.logkat.writers

import com.serebit.logkat.LogLevel
import com.serebit.logkat.Logger

/**
 * Defines an object that receives messages from the [Logger] and writes them to an output vector, such as the
 * console or a file.
 *
 * @see ConsoleWriter
 * @see FileWriter
 * @see MultiWriter
 */
interface MessageWriter {
    /**
     * Writes the given [message] to the output vector.
     */
    fun write(message: String, level: LogLevel)
}

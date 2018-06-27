package com.serebit.loggerkt.writers

import com.serebit.loggerkt.Logger

/**
 * Defines an object that receives messages from the [Logger] and writes them to an output vector, such as the
 * console or a file.
 *
 * @see com.serebit.loggerkt.writers.ConsoleWriter
 * @see com.serebit.loggerkt.writers.FileWriter
 * @see com.serebit.loggerkt.writers.StreamWriter
 * @see com.serebit.loggerkt.writers.MultiWriter
 */
interface LogWriter {
    /**
     * Writes the given [message] to the output vector.
     */
    fun write(message: String)
}

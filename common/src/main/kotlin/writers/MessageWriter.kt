package com.serebit.loggerkt.writers

/**
 * Defines an object that receives messages from the [com.serebit.loggerkt.Logger] and writes them to an output vector,
 * such as the console or a file.
 *
 * @see com.serebit.loggerkt.writers.ConsoleWriter
 * @see com.serebit.loggerkt.writers.FileWriter
 * @see com.serebit.loggerkt.writers.MultiWriter
 */
interface MessageWriter {
    /**
     * Writes the given [message] to the output vector.
     */
    fun write(message: String)
}

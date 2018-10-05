package com.serebit.logkat.writers

/**
 * Defines an object that receives messages from the [com.serebit.logkat.Logger] and writes them to an output vector,
 * such as the console or a file.
 *
 * @see com.serebit.logkat.writers.ConsoleWriter
 * @see com.serebit.logkat.writers.FileWriter
 * @see com.serebit.logkat.writers.MultiWriter
 */
interface MessageWriter {
    /**
     * Writes the given [message] to the output vector.
     */
    fun write(message: String)
}

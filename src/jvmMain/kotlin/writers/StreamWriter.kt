package com.serebit.logkat.writers

import com.serebit.logkat.LogLevel
import java.io.OutputStream
import java.io.PrintStream

/**
 * An implementation of [MessageWriter] that outputs to the given stream.
 *
 * @constructor Creates a new instance with the specified [OutputStream].
 * @param output The output stream to be printed to.
 */
class StreamWriter(output: OutputStream) : MessageWriter {
    private val stream = PrintStream(output)

    /**
     * Prints the given [message] to the stream.
     */
    override fun write(message: String, level: LogLevel) = stream.println(message)
}

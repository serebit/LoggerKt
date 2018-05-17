package com.serebit.loggerkt.writers

import com.serebit.loggerkt.LogMessage
import java.io.PrintStream

/**
 * An implementation of [LogWriter] that outputs to the given stream. This writer's output is not color-coded,
 * as that property is exclusive to [ConsoleWriter].
 *
 * @constructor Creates a new instance with the specified stream.
 * @param stream The output stream to be printed to.
 */
class PrintStreamWriter(private val stream: PrintStream) : LogWriter {
    /**
     * Prints the given [message] to the stream.
     */
    override fun log(message: LogMessage) {
        stream.println(message.text)
    }
}

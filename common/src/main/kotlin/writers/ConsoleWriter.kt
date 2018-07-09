package com.serebit.loggerkt.writers

import com.serebit.loggerkt.LogLevel
import com.serebit.loggerkt.platform.Platform

/**
 * An implementation of [LogWriter] that outputs to the console. This writer's output is color coded using ANSI
 * escape codes by default, unless the operating system is Windows.
 *
 * @constructor Creates a new instance, using ANSI color transforms if specified.
 * @param shouldUseAnsiColors Whether or not the writer should use ANSI color transforms. Defaults to true.
 */
class ConsoleWriter constructor(shouldUseAnsiColors: Boolean = true) : LogWriter {
    private val useAnsiColors = if (Platform.supportsAnsiColors) false else shouldUseAnsiColors

    /**
     * Prints the given [message] to the console, applying the proper ANSI color transform if necessary.
     */
    override fun write(message: String) = println(message)

    internal fun write(message: String, level: LogLevel) = println(message.let(level.ansiColorTransform))
}

package com.serebit.logkat.writers

import com.serebit.logkat.LogLevel
import com.serebit.logkat.platform.Platform

/**
 * An implementation of [MessageWriter] that outputs to the console. This writer's output is color coded using ANSI
 * escape codes by default, unless the operating system is Windows.
 *
 * @constructor Creates a new instance, using ANSI color transforms if specified.
 * @param coloredOutput Whether or not the writer should use ANSI color transforms. Defaults to true.
 */
class ConsoleWriter(coloredOutput: Boolean = true) : MessageWriter {
    private val useAnsiColors = if (!Platform.supportsAnsiColors) false else coloredOutput

    override fun write(message: String, level: LogLevel) =
        println(message.let { if (useAnsiColors) level.colorTransform(it) else it })
}

package com.serebit.logkat

import com.serebit.logkat.writers.ConsoleWriter
import com.serebit.logkat.writers.MessageWriter
import com.soywiz.klock.DateTime

/**
 * The main logging class, through which messages are processed and sent to an output vector.
 * This class can be configured at runtime using several utility functions and properties.
 *
 * @property level The logLevel from which the logger will output log messages. Defaults to [LogLevel.WARNING].
 * @property writer The [MessageWriter] that will be used to output log messages. Can be any predefined MessageWriter,
 * or a custom implementation.
 * @property messageFormat The log message formatter.
 */
class Logger(
    var level: LogLevel = LogLevel.WARNING,
    var writer: MessageWriter = ConsoleWriter(),
    var messageFormat: (FormatterPayload) -> String = { "${it.timestamp} ${it.level}: ${it.message}" }
) {

    /**
     * Used in conjunction with the [messageFormat] to format the contents of each log message.
     *
     * @property timestamp The time of the log message.
     * @property level The [LogLevel] of the message.
     * @property message The content of the message.
     */
    data class FormatterPayload(val timestamp: String, val level: LogLevel, val message: String)

    /**
     * Convenience variable for setting the format of the timestamp used in log messages.
     * Defaults to the ISO-8601 date and time formats.
     */
    var timestampFormat: DateTime.() -> String = {
        val newMonth = yearMonth.month1.toString().padStart(2, '0')
        val newDay = dayOfMonth.toString().padStart(2, '0')
        val newHours = hours.toString().padStart(2, '0')
        val newMinutes = minutes.toString().padStart(2, '0')
        val newSeconds = seconds.toString().padStart(2, '0')
        val newMilliseconds = milliseconds.toString().padStart(3, '0')
        "${year.year}-$newMonth-$newDay $newHours:$newMinutes:$newSeconds.$newMilliseconds"
    }

    /** Logs a [message] with the given [level], unless the given [level] is [LogLevel.OFF]. */
    fun log(level: LogLevel, message: String) {
        if (level >= this.level && this.level != LogLevel.OFF) {
            val timestamp = DateTime.now().timestampFormat()
            val formattedMessage = FormatterPayload(timestamp, level, message).let(messageFormat)
            writer.write(formattedMessage, level)
        }
    }

}

/** Logs a [message] with the level [DEBUG][LogLevel.DEBUG]. */
fun Logger.debug(message: String): Unit = log(LogLevel.DEBUG, message)

/** Logs a [message] with the level [INFO][LogLevel.INFO]. */
fun Logger.info(message: String): Unit = log(LogLevel.INFO, message)

/** Logs a [message] with the level [WARNING][LogLevel.WARNING]. */
fun Logger.warn(message: String): Unit = log(LogLevel.WARNING, message)

/** Logs a [message] with the level [ERROR][LogLevel.ERROR]. */
fun Logger.error(message: String): Unit = log(LogLevel.ERROR, message)

/** Logs a [message] with the level [TRACE][LogLevel.TRACE]. */
fun Logger.trace(message: String): Unit = log(LogLevel.TRACE, message)

/** Logs a [message] with the level [FATAL][LogLevel.FATAL]. */
fun Logger.fatal(message: String): Unit = log(LogLevel.FATAL, message)

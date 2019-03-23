package com.serebit.logkat

import com.serebit.logkat.writers.ConsoleWriter
import com.serebit.logkat.writers.MessageWriter
import com.soywiz.klock.DateTime

/**
 * The main logging class, through which messages are processed and sent to an output vector. This class can be
 * configured at runtime using several utility functions and properties.
 */
class Logger {
    /**
     * Convenience variable for setting the format of the timestamp used in log messages. Defaults to the ISO-8601
     * date and time formats.
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
    /**
     * The [LogLevel] from which the logger will output log messages. Defaults to [LogLevel.WARNING].
     */
    var level: LogLevel = LogLevel.WARNING
    /**
     * The log message formatter.
     */
    var messageFormat: FormatterPayload.() -> String = { "$timestamp $level: $message" }
    /**
     * The [MessageWriter] that will be used to output log messages. Can be any predefined MessageWriter, or a custom
     * implementation.
     */
    var writer: MessageWriter = ConsoleWriter()

    /**
     * Logs a [message] with the level [TRACE][LogLevel.TRACE].
     */
    fun trace(message: String) = log(LogLevel.TRACE, message)

    /**
     * Logs a [message] with the level [DEBUG][LogLevel.DEBUG].
     */
    fun debug(message: String) = log(LogLevel.DEBUG, message)

    /**
     * Logs a [message] with the level [INFO][LogLevel.INFO].
     */
    fun info(message: String) = log(LogLevel.INFO, message)

    /**
     * Logs a [message] with the level [WARNING][LogLevel.WARNING].
     */
    fun warn(message: String) = log(LogLevel.WARNING, message)

    /**
     * Logs a [message] with the level [ERROR][LogLevel.ERROR].
     */
    fun error(message: String) = log(LogLevel.ERROR, message)

    /**
     * Logs a [message] with the level [FATAL][LogLevel.FATAL].
     */
    fun fatal(message: String) = log(LogLevel.FATAL, message)

    /**
     * Logs a [message] with the given [level], unless the given [level] is [LogLevel.OFF].
     */
    fun log(level: LogLevel, message: String) {
        if (level >= this.level && this.level != LogLevel.OFF) {
            val timestamp = DateTime.now().timestampFormat()
            val formattedMessage = FormatterPayload(timestamp, level, message).let(messageFormat)
            writer.write(formattedMessage, level)
        }
    }
}

data class FormatterPayload(val timestamp: String, val level: LogLevel, val message: String)

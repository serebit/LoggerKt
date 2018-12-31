package com.serebit.logkat

import com.serebit.logkat.formatting.FormatterPayload
import com.serebit.logkat.time.DateTime
import com.serebit.logkat.time.now
import com.serebit.logkat.writers.ConsoleWriter
import com.serebit.logkat.writers.MessageWriter

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
        "$year-$month-$day $hour:$minute:$second.$millisecond"
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
     *
     * @param message The message to log.
     */
    fun trace(message: String) = log(LogLevel.TRACE, message)

    /**
     * Logs a [message] with the level [DEBUG][LogLevel.DEBUG].
     *
     * @param message The message to log.
     */
    fun debug(message: String) = log(LogLevel.DEBUG, message)

    /**
     * Logs a [message] with the level [INFO][LogLevel.INFO].
     *
     * @param message The message to log.
     */
    fun info(message: String) = log(LogLevel.INFO, message)

    /**
     * Logs a [message] with the level [WARNING][LogLevel.WARNING].
     *
     * @param message The message to log.
     */
    fun warn(message: String) = log(LogLevel.WARNING, message)

    /**
     * Logs a [message] with the level [ERROR][LogLevel.ERROR].
     *
     * @param message The message to log.
     */
    fun error(message: String) = log(LogLevel.ERROR, message)

    /**
     * Logs a [message] with the level [FATAL][LogLevel.FATAL].
     *
     * @param message The message to log.
     */
    fun fatal(message: String) = log(LogLevel.FATAL, message)

    /**
     * Logs a [message] with the given [level], unless the given [level] is [LogLevel.OFF].
     */
    fun log(level: LogLevel, message: String) {
        /*
        if the message's level is higher than or equal to the level setting, and the level setting isn't OFF, write
        it to the output vector
        */
        if (level >= this.level && this.level != LogLevel.OFF) {
            val timestamp = now().timestampFormat()
            val formattedMessage = FormatterPayload(timestamp, level, message).let(messageFormat)
            writer.write(formattedMessage, level)
        }
    }
}

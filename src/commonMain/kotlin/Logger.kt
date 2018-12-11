package com.serebit.logkat

import com.serebit.logkat.formatting.FormatterPayload
import com.serebit.logkat.formatting.TimestampGenerator
import com.serebit.logkat.writers.ConsoleWriter
import com.serebit.logkat.writers.MessageWriter

/**
 * The main logging class, through which messages are processed and sent to an output vector. This object can be
 * configured at runtime, extended, and instantiated.
 */
class Logger {
    private var timestampGenerator = TimestampGenerator(TimestampGenerator.DEFAULT_PATTERN)
    /**
     * Convenience variable for setting the pattern of the timestamp sent to the [formatter].
     */
    var timestampPattern: String
        get() = timestampGenerator.pattern
        set(value) {
            timestampGenerator.pattern = value
        }
    /**
     * The [LogLevel] from which the logger will output log messages. Defaults to [LogLevel.WARNING].
     */
    var level: LogLevel = LogLevel.WARNING
    /**
     * The log message formatter.
     */
    var formatter: (FormatterPayload) -> String = { (time, threadName, level, message) ->
        "$time [$threadName] $level: $message"
    }
    /**
     * The [MessageWriter] that will be used to output log messages. Can be any predefined MessageWriter, or a custom
     * implementation.
     */
    var writer: MessageWriter = ConsoleWriter(true)

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

    private fun log(level: LogLevel, message: String) {
        // if the message's level is higher than or equal to the level setting, write it to the output vector
        if (level >= this.level) writeLog(level, message)
    }

    private fun writeLog(level: LogLevel, message: String) {
        // example: 2018-01-12 21:03:25 [main] INFO: Logged Message
        val formattedMessage = FormatterPayload(timestampGenerator, level, message).let(formatter)
        writer.write(formattedMessage, level)
    }
}

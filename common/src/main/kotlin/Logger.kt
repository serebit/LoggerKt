package com.serebit.loggerkt

import com.serebit.loggerkt.formatting.FormatterPayload
import com.serebit.loggerkt.formatting.FormatterPayloadGenerator
import com.serebit.loggerkt.formatting.TimestampGenerator
import com.serebit.loggerkt.writers.ConsoleWriter
import com.serebit.loggerkt.writers.LogWriter
import kotlinx.coroutines.experimental.launch

/**
 * The logger singleton. This object is both used for logging and for logger configuration.
 */
object Logger {
    private var timestampGenerator = TimestampGenerator("yyyy-MM-dd HH:mm:ss")
    /**
     * Determines whether logs should be written asynchronously via coroutines. While this does provide significant
     * performance improvements, logs just before a program exit may not be written, so this defaults to false.
     */
    var async: Boolean = false
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
    var formatter: (FormatterPayload) -> String = { (time, threadName, className, methodName, level, message) ->
        "$time [$threadName] ($className.$methodName) $level: $message"
    }
    /**
     * The [LogWriter] that will be used to output log messages. Can be any predefined LogWriter, or a custom
     * implementation.
     */
    var writer: LogWriter = ConsoleWriter()

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
        // check if the message should actually be logged
        if (this.level > level) return
        if (async) launch {
            writeLog(level, message)
        } else writeLog(level, message)
    }

    private fun writeLog(level: LogLevel, message: String) {
        // example: 2018-01-12 21:03:25 [main] (TestKt.main) INFO: Logged Message
        FormatterPayloadGenerator.generate(timestampGenerator, level, message)
            .let(formatter)
            .let { formattedMessage ->
                (writer as? ConsoleWriter)?.write(formattedMessage, level) ?: writer.write(formattedMessage)
            }
    }
}

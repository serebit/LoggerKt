package com.serebit.loggerkt

import com.serebit.loggerkt.writers.ConsoleWriter
import com.serebit.loggerkt.writers.LogWriter
import kotlinx.coroutines.experimental.launch
import java.time.format.DateTimeFormatter

/**
 * The logger singleton. This object is both used for logging and for logger configuration.
 */
object Logger {
    private val timestampFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    /**
     * Determines whether logs should be written asynchronously via coroutines. While this does provide significant
     * performance improvements, logs just before a program exit may not be written, so this defaults to false.
     */
    @JvmStatic
    var async: Boolean = false
    /**
     * The [LogLevel] from which the logger will output log messages. Defaults to [LogLevel.INFO].
     */
    @JvmStatic
    var level: LogLevel = LogLevel.INFO
    /**
     * The log message formatter.
     */
    @JvmStatic
    var formatter: (FormatterPayload) -> String = { (time, threadName, className, methodName, level, message) ->
        "${time.format(timestampFormat)} [$threadName] ($className.$methodName) $level: $message"
    }
    /**
     * The [LogWriter] that will be used to output log messages. Can be any predefined LogWriter, or a custom
     * implementation.
     */
    @JvmStatic
    var writer: LogWriter = ConsoleWriter()

    /**
     * Logs a [message] with the level [TRACE][LogLevel.TRACE].
     *
     * @param message The message to log.
     */
    @JvmStatic
    fun trace(message: String) = log(LogLevel.TRACE, message)

    /**
     * Logs a [message] with the level [DEBUG][LogLevel.DEBUG].
     *
     * @param message The message to log.
     */
    @JvmStatic
    fun debug(message: String) = log(LogLevel.DEBUG, message)

    /**
     * Logs a [message] with the level [INFO][LogLevel.INFO].
     *
     * @param message The message to log.
     */
    @JvmStatic
    fun info(message: String) = log(LogLevel.INFO, message)

    /**
     * Logs a [message] with the level [WARNING][LogLevel.WARNING].
     *
     * @param message The message to log.
     */
    @JvmStatic
    fun warn(message: String) = log(LogLevel.WARNING, message)

    /**
     * Logs a [message] with the level [ERROR][LogLevel.ERROR].
     *
     * @param message The message to log.
     */
    @JvmStatic
    fun error(message: String) = log(LogLevel.ERROR, message)

    /**
     * Logs a [message] with the level [FATAL][LogLevel.FATAL].
     *
     * @param message The message to log.
     */
    @JvmStatic
    fun fatal(message: String) = log(LogLevel.FATAL, message)

    private fun log(level: LogLevel, message: String) {
        // check if the message should actually be logged
        if (LogLevel.values().indexOf(this.level) > LogLevel.values().indexOf(level)) return
        if (async) launch {
            writeLog(level, message)
        } else writeLog(level, message)
    }

    private fun writeLog(level: LogLevel, message: String) {
        // example: 2018-01-12 21:03:25 [main] (TestKt.main) INFO: Logged Message
        formatter(FormatterPayload.generate(level, message)).let { formattedMessage ->
            if (writer is ConsoleWriter) writer.log(LeveledLogMessage(formattedMessage, level))
            else writer.log(SimpleLogMessage(formattedMessage))
        }
    }
}

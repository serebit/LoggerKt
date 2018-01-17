package com.serebit.loggerkt

import com.serebit.loggerkt.writers.ConsoleWriter
import com.serebit.loggerkt.writers.LogWriter
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

/**
 * The logger singleton. This object is both used for actual logging and for logger configuration.
 */
object Logger {
    private val timestampFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    /**
     * The [LogLevel] from which the logger will output log messages. Defaults to [LogLevel.INFO].
     */
    @JvmStatic
    var level: LogLevel = LogLevel.INFO
    /**
     * The log message format.
     */
    @JvmStatic
    var format = { time: OffsetDateTime,
                   thread: String, className: String, method: String,
                   level: LogLevel,
                   message: String ->
        "${time.format(timestampFormat)} [$thread] ($className.$method) $level: $message"
    }
    /**
     * The [LogWriter] that will be used to output log messages. Can be any predefined LogWriter, or a custom
     * implementation.
     */
    @JvmStatic
    var writer: LogWriter = ConsoleWriter()

    /**
     * Logs a [message] with the level [TRACE][LogLevel.TRACE].
     */
    @JvmStatic
    fun trace(message: String) = log(LogLevel.TRACE, message)

    /**
     * Logs a [message] with the level [DEBUG][LogLevel.DEBUG].
     */
    @JvmStatic
    fun debug(message: String) = log(LogLevel.DEBUG, message)

    /**
     * Logs a [message] with the level [INFO][LogLevel.INFO].
     */
    @JvmStatic
    fun info(message: String) = log(LogLevel.INFO, message)

    /**
     * Logs a [message] with the level [WARNING][LogLevel.WARNING].
     */
    @JvmStatic
    fun warn(message: String) = log(LogLevel.WARNING, message)

    /**
     * Logs a [message] with the level [ERROR][LogLevel.ERROR].
     */
    @JvmStatic
    fun error(message: String) = log(LogLevel.ERROR, message)

    private fun log(level: LogLevel, message: String) {
        // check if the message should actually be logged
        if (LogLevel.values().indexOf(this.level) > LogLevel.values().indexOf(level)) return
        val thread = Thread.currentThread()
        val (className, methodName) = thread.stackTrace[3].let { it.className to it.methodName }
        // example: 2018-01-12 21:03:25 [main] (TestKt.main) INFO: Logged Message
        format(
            OffsetDateTime.now(),
            thread.name, className, methodName,
            level,
            message
        ).let { formattedMessage ->
            writer.log(level, formattedMessage)
        }
    }
}
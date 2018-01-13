package com.serebit.loggerkt

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
    @JvmStatic var level: LogLevel = LogLevel.INFO
    /**
     * The log message format.
     */
    @JvmStatic var format = { time: OffsetDateTime, thread: String, className: String, method: String, level: LogLevel, message: String ->
        "${time.format(timestampFormat)} [$thread] ($className.$method) $level: $message"
    }
    /**
     * The [LogWriter] that will be used to output log messages. Can be any predefined LogWriter, or a custom
     * implementation.
     */
    @JvmStatic var writer: LogWriter = ConsoleWriter()

    /**
     * Logs a message with the level [TRACE][LogLevel.TRACE].
     *
     * @param message The message to log.
     */
    @JvmStatic fun trace(message: String) = log(LogLevel.TRACE, message)

    /**
     * Logs a message with the level [DEBUG][LogLevel.DEBUG].
     *
     * @param message The message to log.
     */
    @JvmStatic fun debug(message: String) = log(LogLevel.DEBUG, message)

    /**
     * Logs a message with the level [INFO][LogLevel.INFO].
     *
     * @param message The message to log.
     */
    @JvmStatic fun info(message: String) = log(LogLevel.INFO, message)

    /**
     * Logs a message with the level [WARNING][LogLevel.WARNING].
     *
     * @param message The message to log.
     */
    @JvmStatic fun warn(message: String) = log(LogLevel.WARNING, message)

    /**
     * Logs a message with the level [ERROR][LogLevel.ERROR].
     *
     * @param message The message to log.
     */
    @JvmStatic fun error(message: String) = log(LogLevel.ERROR, message)

    private fun log(level: LogLevel, rawMessage: String) {
        if (LogLevel.values().indexOf(this.level) > LogLevel.values().indexOf(level)) return
        val time = OffsetDateTime.now()
        val thread = Thread.currentThread()
        val (className, methodName) = thread.stackTrace[3].let { it.className to it.methodName }
        // example: 2018-01-12 21:03:25 [main] TestKt.main
        val message = format(time, thread.name, className, methodName, level, rawMessage)
        when(writer) {
            is ConsoleWriter -> writer.log(message.let(level.ansiColorTransform))
            is FileWriter -> writer.log(message)
        }
    }
}
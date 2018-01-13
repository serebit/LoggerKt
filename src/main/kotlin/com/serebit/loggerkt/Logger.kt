package com.serebit.loggerkt

import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

object Logger {
    private val timestampFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    var format = { time: OffsetDateTime, thread: String, className: String, method: String, level: Level, message: String ->
        "${time.format(timestampFormat)} [$thread] ($className.$method) $level: $message"
    }
    var writer: LogWriter = ConsoleWriter()

    /**
     * Logs a message with the level [TRACE][Level.TRACE].
     *
     * @param message The message to log.
     */
    fun trace(message: String) = log(Level.TRACE, message)

    /**
     * Logs a message with the level [DEBUG][Level.DEBUG].
     *
     * @param message The message to log.
     */
    fun debug(message: String) = log(Level.DEBUG, message)

    /**
     * Logs a message with the level [INFO][Level.INFO].
     *
     * @param message The message to log.
     */
    fun info(message: String) = log(Level.INFO, message)

    /**
     * Logs a message with the level [WARNING][Level.WARNING].
     *
     * @param message The message to log.
     */
    fun warn(message: String) = log(Level.WARNING, message)

    /**
     * Logs a message with the level [ERROR][Level.ERROR].
     *
     * @param message The message to log.
     */
    fun error(message: String) = log(Level.ERROR, message)

    private fun log(level: Level, rawMessage: String) {
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
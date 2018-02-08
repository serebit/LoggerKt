package com.serebit.loggerkt

/**
 * The wrapper class around a log message. Implementations must include an override of the message [text], and can
 * include accompanying data.
 */
sealed class LogMessage {
    abstract val text: String
}

/**
 * The simplest implementation of LogMessage, with its only argument being the message [text], and no accompanying data.
 */
data class SimpleLogMessage(override val text: String) : LogMessage() {
    override fun toString(): String = text
}

/**
 * An implementation of LogMessage that includes the [level] of the message, along with the message [text].
 */
data class LeveledLogMessage(override val text: String, private val level: LogLevel) : LogMessage() {
    val withAnsiColorTransform get() = copy(text = text.let(level.ansiColorTransform))

    override fun toString(): String = text
}
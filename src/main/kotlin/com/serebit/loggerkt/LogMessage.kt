package com.serebit.loggerkt

/**
 * The wrapper class around a log message. Implementations must include an override of the message [text], and can
 * include accompanying data.
 */
sealed class LogMessage {
    /**
     * The text contained by this instance.
     */
    abstract val text: String

    /**
     * The simplest implementation of LogMessage, with its only argument being the message [text].
     *
     * @constructor Creates a new instance with the given [text].
     */
    data class Simple(override val text: String) : LogMessage() {
        override fun toString(): String = text
    }

    /**
     * An implementation of LogMessage that includes the [LogLevel] of the message, along with the message [text].
     *
     * @constructor Creates a new instance with the given [text] and level.
     */
    data class Leveled(override val text: String, private val level: LogLevel) : LogMessage() {
        /**
         * A property that returns a copy of this instance with the respective ANSI color transformation applied to it.
         */
        val withAnsiColorTransform get() = copy(text = text.let(level.ansiColorTransform))

        override fun toString(): String = text
    }
}



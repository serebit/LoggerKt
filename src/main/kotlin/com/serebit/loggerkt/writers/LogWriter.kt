package com.serebit.loggerkt.writers

import com.serebit.loggerkt.LogMessage

/**
 * Defines an object that is capable of taking [LogMessage]s and outputting them.
 */
interface LogWriter {
    /**
     * Logs the given [message].
     */
    fun log(message: LogMessage)
}

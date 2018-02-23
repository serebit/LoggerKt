package com.serebit.loggerkt.writers

import com.serebit.loggerkt.LogMessage

/**
 * The interface which all LogWriters inherit.
 */
interface LogWriter {
    /**
     * Logs the given [message].
     */
    fun log(message: LogMessage)
}

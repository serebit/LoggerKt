package com.serebit.loggerkt.formatting

import com.serebit.loggerkt.LogLevel

data class FormatterPayload(
    val timestamp: String,
    val threadName: String, val className: String, val methodName: String,
    val level: LogLevel,
    val message: String
)

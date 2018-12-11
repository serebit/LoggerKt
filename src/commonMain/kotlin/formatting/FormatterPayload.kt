package com.serebit.logkat.formatting

import com.serebit.logkat.LogLevel

data class FormatterPayload(val timestamp: String, val level: LogLevel, val message: String)

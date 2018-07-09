package com.serebit.loggerkt.platform

import java.io.File

internal actual object Platform {
    actual val classpath: String = File(this::class.java.protectionDomain.codeSource.location.path).parent
    actual val supportsAnsiColors: Boolean = System.getProperty("os.name").startsWith("Windows")
}

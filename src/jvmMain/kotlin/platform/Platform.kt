package com.serebit.logkat.platform

import java.io.File

internal actual object Platform {
    actual val classpath: String = File(this::class.java.protectionDomain.codeSource.location.path).parent
    actual val supportsAnsiColors = !System.getProperty("os.name").startsWith("Windows")
    actual val lineSeparator = System.lineSeparator()
    actual val pathSeparator = File.pathSeparatorChar
}

package com.serebit.logkat.platform

internal actual class File actual constructor(path: String) {
    actual val absolutePath: String
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    actual fun appendText(text: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    actual fun readText(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    actual fun delete(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    actual companion object {
        actual val classpath: String
            get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        actual val pathSeparator = '\\'

        actual fun createTempFile(prefix: String): File {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }
}

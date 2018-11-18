import com.serebit.logkat.LogLevel
import com.serebit.logkat.Logger
import com.serebit.logkat.writers.MessageWriter
import kotlin.test.Test
import kotlin.test.BeforeTest
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class LoggerTest {
    @Test
    fun `Logger should log messages`() {
        Logger.info("Test log string")
        assertTrue { (Logger.writer as TestWriter).log.isNotBlank() }
    }

    @Test
    fun `Logger should log the text given to it`() {
        Logger.info("Test log string")
        assertEquals((Logger.writer as TestWriter).log, "INFO: Test log string\n")
    }

    @Test
    fun `Logger should log the correct LogLevels`() {
        Logger.formatter = { it.level.toString() }
        Logger.level = LogLevel.TRACE
        Logger.trace("")
        Logger.debug("")
        Logger.info("")
        Logger.warn("")
        Logger.error("")
        Logger.fatal("")
        assertEquals((Logger.writer as TestWriter).log, "TRACE\nDEBUG\nINFO\nWARNING\nERROR\nFATAL\n")
    }

    @Test
    fun `Logger should ignore messages below the set LogLevel`() {
        Logger.level = LogLevel.INFO
        Logger.debug("Test debug string")
        Logger.trace("Test trace string")
        Logger.info("Test info string")
        assertEquals((Logger.writer as TestWriter).log, "INFO: Test info string\n")
    }

    @Test
    fun `Logger should be able to log messages of all levels`() {
        Logger.level = LogLevel.TRACE
        Logger.trace("Test trace string")
        Logger.debug("Test debug string")
        Logger.info("Test info string")
        Logger.warn("Test warning string")
        Logger.error("Test error string")
        Logger.fatal("Test fatal string")
        assertEquals(
            (Logger.writer as TestWriter).log, """
                TRACE: Test trace string
                DEBUG: Test debug string
                INFO: Test info string
                WARNING: Test warning string
                ERROR: Test error string
                FATAL: Test fatal string

            """.trimIndent()
        )
    }

    private class TestWriter : MessageWriter {
        var log: String = ""
            private set

        override fun write(message: String, level: LogLevel) {
            log += "$message\n"
        }
    }

    @BeforeTest
    fun beforeTest() {
        Logger.level = LogLevel.INFO
        Logger.writer = TestWriter()
        Logger.formatter = { (_, _, level, message) -> "$level: $message" }
    }
}

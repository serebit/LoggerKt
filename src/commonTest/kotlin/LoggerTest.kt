import com.serebit.logkat.LogLevel
import com.serebit.logkat.Logger
import com.serebit.logkat.writers.MessageWriter
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class LoggerTest {
    private val logger = Logger().apply {
        level = LogLevel.INFO
        writer = TestWriter()
        formatter = { "$level: $message" }
    }

    @Test
    fun `should log messages`() {
        logger.info("Test log string")
        assertTrue { (logger.writer as TestWriter).log.isNotBlank() }
    }

    @Test
    fun `should log correct text`() {
        logger.info("Test log string")
        assertEquals((logger.writer as TestWriter).log, "INFO: Test log string\n")
    }

    @Test
    fun `should log correct levels`() {
        logger.formatter = { level.toString() }
        logger.level = LogLevel.TRACE
        logger.trace("")
        logger.debug("")
        logger.info("")
        logger.warn("")
        logger.error("")
        logger.fatal("")
        assertEquals((logger.writer as TestWriter).log, "TRACE\nDEBUG\nINFO\nWARNING\nERROR\nFATAL\n")
    }

    @Test
    fun `should ignore messages below set level`() {
        logger.level = LogLevel.INFO
        logger.debug("Test debug string")
        logger.trace("Test trace string")
        logger.info("Test info string")
        assertEquals((logger.writer as TestWriter).log, "INFO: Test info string\n")
    }

    @Test
    fun `should not log messages with the level OFF`() {
        logger.level = LogLevel.OFF
        logger.fatal("Test fatal string")
        assertTrue((logger.writer as TestWriter).log.isEmpty())
    }

    @Test
    fun `should log messages of all levels`() {
        logger.level = LogLevel.TRACE
        logger.trace("Test trace string")
        logger.debug("Test debug string")
        logger.info("Test info string")
        logger.warn("Test warning string")
        logger.error("Test error string")
        logger.fatal("Test fatal string")
        assertEquals(
            (logger.writer as TestWriter).log, """
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
}

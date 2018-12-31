import com.serebit.logkat.LogLevel
import com.serebit.logkat.Logger
import com.serebit.logkat.writers.BufferWriter
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class LoggerTest {
    private val buffer = BufferWriter()
    private val logger = Logger().apply {
        level = LogLevel.INFO
        writer = buffer
        messageFormat = { "$level: $message" }
    }

    @Test
    fun `should log messages`() {
        logger.info("Test log string")
        assertTrue { buffer.read().isNotBlank() }
    }

    @Test
    fun `should log correct text`() {
        logger.info("Test log string")
        assertEquals(
            buffer.read(), """
                INFO: Test log string

            """.trimIndent()
        )
    }

    @Test
    fun `should log correct levels`() {
        logger.messageFormat = { level.toString() }
        logger.level = LogLevel.TRACE
        logger.trace("")
        logger.debug("")
        logger.info("")
        logger.warn("")
        logger.error("")
        logger.fatal("")
        assertEquals(
            (logger.writer as BufferWriter).read(),
            """
                TRACE
                DEBUG
                INFO
                WARNING
                ERROR
                FATAL

            """.trimIndent()
        )
    }

    @Test
    fun `should ignore messages below set level`() {
        logger.level = LogLevel.INFO
        logger.debug("Test debug string")
        logger.trace("Test trace string")
        logger.info("Test info string")
        assertEquals(buffer.read(), "INFO: Test info string\n")
    }

    @Test
    fun `should not log messages with the level OFF`() {
        logger.level = LogLevel.OFF
        logger.fatal("Test fatal string")
        assertTrue(buffer.read().isEmpty())
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
            buffer.read(), """
                TRACE: Test trace string
                DEBUG: Test debug string
                INFO: Test info string
                WARNING: Test warning string
                ERROR: Test error string
                FATAL: Test fatal string

            """.trimIndent()
        )
    }

    @BeforeTest
    fun clearBuffer() {
        buffer.clear()
    }
}


import com.serebit.logkat.LogLevel
import com.serebit.logkat.Logger
import com.serebit.logkat.internal.Platform
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
    private val ls = Platform.lineSeparator

    @Test
    fun `should log messages`() {
        logger.info("test")
        assertTrue(buffer.read().isNotBlank())
    }

    @Test
    fun `should log correct text`() {
        logger.info("test")
        assertEquals(
            "INFO: test$ls", buffer.read()
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
        assertEquals("TRACE${ls}DEBUG${ls}INFO${ls}WARNING${ls}ERROR${ls}FATAL$ls", buffer.read())
    }

    @Test
    fun `should ignore messages below set level`() {
        logger.level = LogLevel.INFO
        logger.debug("test")
        logger.trace("test")
        logger.info("test")
        assertEquals("INFO: test$ls", buffer.read())
    }

    @Test
    fun `should not log messages with the level OFF`() {
        logger.level = LogLevel.OFF
        logger.fatal("test")
        assertTrue(buffer.read().isEmpty())
    }

    @Test
    fun `should log messages of all levels`() {
        logger.level = LogLevel.TRACE
        logger.trace("test")
        logger.debug("test")
        logger.info("test")
        logger.warn("test")
        logger.error("test")
        logger.fatal("test")
        assertEquals(
            "TRACE: test${ls}DEBUG: test${ls}INFO: test${ls}WARNING: test${ls}ERROR: test${ls}FATAL: test$ls",
            buffer.read()
        )
    }

    @BeforeTest
    fun clearBuffer() {
        buffer.clear()
    }
}

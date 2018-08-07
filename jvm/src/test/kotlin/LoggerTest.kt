import com.serebit.loggerkt.LogLevel
import com.serebit.loggerkt.Logger
import com.serebit.loggerkt.writers.MessageWriter
import io.kotlintest.TestCaseContext
import io.kotlintest.matchers.should
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec

class LoggerTest : StringSpec() {
    init {
        "Logger should log messages" {
            Logger.info("Test log string")
            (Logger.writer as TestWriter).log should { it.isNotEmpty() }
        }

        "Logger should log the text given to it" {
            Logger.info("Test log string")
            (Logger.writer as TestWriter).log shouldBe "INFO: Test log string\n"
        }

        "Logger should log the correct LogLevels" {
            Logger.formatter = { it.level.toString() }
            Logger.level = LogLevel.TRACE
            Logger.trace("")
            Logger.debug("")
            Logger.info("")
            Logger.warn("")
            Logger.error("")
            Logger.fatal("")
            (Logger.writer as TestWriter).log shouldBe "TRACE\nDEBUG\nINFO\nWARNING\nERROR\nFATAL\n"
        }

        "Logger should ignore messages below the set LogLevel" {
            Logger.level = LogLevel.INFO
            Logger.debug("Test debug string")
            Logger.trace("Test trace string")
            Logger.info("Test info string")
            (Logger.writer as TestWriter).log shouldBe "INFO: Test info string\n"
        }

        "Logger should be able to log messages of all levels" {
            Logger.level = LogLevel.TRACE
            Logger.trace("Test trace string")
            Logger.debug("Test debug string")
            Logger.info("Test info string")
            Logger.warn("Test warning string")
            Logger.error("Test error string")
            Logger.fatal("Test fatal string")
            (Logger.writer as TestWriter).log shouldBe """
                TRACE: Test trace string
                DEBUG: Test debug string
                INFO: Test info string
                WARNING: Test warning string
                ERROR: Test error string
                FATAL: Test fatal string

            """.trimIndent()
        }
    }

    override fun interceptTestCase(context: TestCaseContext, test: () -> Unit) {
        Logger.level = LogLevel.INFO
        Logger.writer = TestWriter()
        Logger.formatter = { (_, _, level, message) -> "$level: $message" }
        test()
    }

    private class TestWriter : MessageWriter {
        var log: String = ""
            private set

        override fun write(message: String) {
            log += "$message\n"
        }
    }
}

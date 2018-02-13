import com.serebit.loggerkt.LogLevel
import com.serebit.loggerkt.LogMessage
import com.serebit.loggerkt.LoggerKt
import com.serebit.loggerkt.writers.LogWriter
import io.kotlintest.TestCaseContext
import io.kotlintest.matchers.should
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec

class LoggerTest : StringSpec() {
    init {
        "Logger should log messages" {
            LoggerKt.info("Test log string")
            (LoggerKt.writer as TestWriter).log should { it.isNotEmpty() }
        }

        "Logger should log the text given to it" {
            LoggerKt.info("Test log string")
            (LoggerKt.writer as TestWriter).log shouldBe "INFO: Test log string\n"
        }

        "Logger should log the correct LogLevels" {
            LoggerKt.format = { _, _, _, _, level, _ ->
                level.toString()
            }
            LoggerKt.level = LogLevel.TRACE
            LoggerKt.trace("")
            LoggerKt.debug("")
            LoggerKt.info("")
            LoggerKt.warn("")
            LoggerKt.error("")
            (LoggerKt.writer as TestWriter).log shouldBe "TRACE\nDEBUG\nINFO\nWARNING\nERROR\n"
        }

        "Logger should ignore messages below the set LogLevel" {
            LoggerKt.level = LogLevel.INFO
            LoggerKt.debug("Test debug string")
            LoggerKt.trace("Test trace string")
            LoggerKt.info("Test info string")
            (LoggerKt.writer as TestWriter).log shouldBe "INFO: Test info string\n"
        }

        "Logger should be able to log messages of all levels" {
            LoggerKt.level = LogLevel.TRACE
            LoggerKt.trace("Test trace string")
            LoggerKt.debug("Test debug string")
            LoggerKt.info("Test info string")
            LoggerKt.warn("Test warning string")
            LoggerKt.error("Test error string")
            LoggerKt.fatal("Test fatal string")
            (LoggerKt.writer as TestWriter).log shouldBe """
                TRACE: Test trace string
                DEBUG: Test debug string
                INFO: Test info string
                WARNING: Test warning string
                ERROR: Test error string
                FATAL: Test fatal string

            """.trimIndent()
        }

        "Logger should output correct thread, className and function" {
            LoggerKt.format = { _, thread, className, method, _, _ ->
                "$thread $className.$method"
            }
            functionForTest()
            (LoggerKt.writer as TestWriter).log shouldBe
                "${Thread.currentThread().name} ${this::class.simpleName}.${::functionForTest.name}\n"
        }
    }

    private fun functionForTest() = LoggerKt.info("")

    override fun interceptTestCase(context: TestCaseContext, test: () -> Unit) {
        LoggerKt.level = LogLevel.INFO
        LoggerKt.writer = TestWriter()
        LoggerKt.format = { _, _, _, _, level, message ->
            "$level: $message"
        }
        test()
    }

    private class TestWriter : LogWriter {
        var log: String = ""
            private set

        override fun log(message: LogMessage) {
            log += "$message\n"
        }
    }
}
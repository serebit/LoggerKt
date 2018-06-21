import com.serebit.loggerkt.LogLevel
import com.serebit.loggerkt.LogMessage
import com.serebit.loggerkt.Logger
import com.serebit.loggerkt.writers.LogWriter
import io.kotlintest.TestCaseContext
import io.kotlintest.matchers.should
import io.kotlintest.matchers.shouldBe
import io.kotlintest.matchers.shouldNotBe
import io.kotlintest.specs.StringSpec

class LoggerTest : StringSpec() {
    init {
        "Logger should write messages" {
            Logger.info("Test write string")
            (Logger.writer as? TestWriter)?.log should { it?.isNotEmpty() }
        }

        "Logger should write the text given to it" {
            Logger.info("Test write string")
            (Logger.writer as? TestWriter)?.log shouldBe "INFO: Test log string\n"
        }

        "Logger should write the correct LogLevels" {
            Logger.formatter = { (_, _, _, _, level, _) ->
                level.toString()
            }
            Logger.level = LogLevel.TRACE
            Logger.trace("")
            Logger.debug("")
            Logger.info("")
            Logger.warn("")
            Logger.error("")
            (Logger.writer as? TestWriter)?.log shouldBe "TRACE\nDEBUG\nINFO\nWARNING\nERROR\n"
        }

        "Logger should ignore messages below the set LogLevel" {
            Logger.level = LogLevel.INFO
            Logger.debug("Test debug string")
            Logger.trace("Test trace string")
            Logger.info("Test info string")
            (Logger.writer as? TestWriter)?.log shouldBe "INFO: Test info string\n"
        }

        "Logger should be able to write messages of all levels" {
            Logger.level = LogLevel.TRACE
            Logger.trace("Test trace string")
            Logger.debug("Test debug string")
            Logger.info("Test info string")
            Logger.warn("Test warning string")
            Logger.error("Test error string")
            Logger.fatal("Test fatal string")
            (Logger.writer as? TestWriter)?.log shouldBe """
                TRACE: Test trace string
                DEBUG: Test debug string
                INFO: Test info string
                WARNING: Test warning string
                ERROR: Test error string
                FATAL: Test fatal string

            """.trimIndent()
        }

        "Logger should output correct thread, className and function" {
            Logger.formatter = { (_, thread, className, method, _, _) ->
                "$thread $className.$method"
            }
            functionForTest()
            (Logger.writer as? TestWriter)?.log shouldBe
                "${Thread.currentThread().name} ${this::class.simpleName}.${::functionForTest.name}\n"
        }

        "Logger should use a separate thread to write if async is true" {
            Logger.formatter = { (_, thread, _, _, _, _) ->
                thread
            }
            Logger.async = true
            Logger.info("")
            (Logger.writer as? TestWriter)?.log shouldNotBe "${Thread.currentThread().name}\n"
        }
    }

    private fun functionForTest() = Logger.info("")

    override fun interceptTestCase(context: TestCaseContext, test: () -> Unit) {
        Logger.level = LogLevel.INFO
        Logger.writer = TestWriter()
        Logger.formatter = { (_, _, _, _, level, message) ->
            "$level: $message"
        }
        test()
    }

    private class TestWriter : LogWriter {
        var log: String = ""
            private set

        override fun write(message: LogMessage) {
            log += "$message\n"
        }
    }
}

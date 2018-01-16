
import com.serebit.loggerkt.LogLevel
import com.serebit.loggerkt.Logger
import com.serebit.loggerkt.writers.LogWriter
import io.kotlintest.TestCaseContext
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec

class LoggerTest : StringSpec() {
    init {
        "Logger should log messages" {
            "Test log string".let { message ->
                Logger.info(message)
                (Logger.writer as TestWriter).log shouldBe "INFO: $message"
            }
        }

        "Logger should ignore messages below the set log level" {
            Logger.level = LogLevel.INFO
            Logger.debug("Test debug string")
            Logger.trace("Test trace string")
            (Logger.writer as TestWriter).log shouldBe ""
        }

        "Logger should output correct class and function" {
            Logger.format = { _, _, className, method, _, _ ->
                "$className.$method"
            }
            functionForTest()
            (Logger.writer as TestWriter).log shouldBe "LoggerTest.functionForTest"
        }
    }

    private fun functionForTest() = Logger.info("")

    override fun interceptTestCase(context: TestCaseContext, test: () -> Unit) {
        Logger.level = LogLevel.INFO
        Logger.writer = TestWriter()
        Logger.format = { _, _, _, _, level, message ->
            "$level: $message"
        }
        test()
    }

    private class TestWriter : LogWriter {
        var log: String = ""
            private set

        override fun log(level: LogLevel, message: String) {
            log += message
        }
    }
}
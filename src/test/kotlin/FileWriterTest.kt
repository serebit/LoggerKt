
import com.serebit.loggerkt.Logger
import com.serebit.loggerkt.writers.FileWriter
import io.kotlintest.TestCaseContext
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec
import java.io.File

class FileWriterTest : StringSpec() {
    private val file = File("/tmp/test.tmp")

    init {
        "Logger should log messages to the set file" {
            Logger.info("My name jeff")
            file.readText().trim() shouldBe "INFO: My name jeff"
        }

        "Logger should log multiple messages to the same file" {
            Logger.info("Test string")
            Logger.info("Second test string")
            file.readText().trim() shouldBe "INFO: Test string\nINFO: Second test string"
        }
    }

    override fun interceptTestCase(context: TestCaseContext, test: () -> Unit) {
        Logger.writer = FileWriter(file.absolutePath, false)
        Logger.format = { _, _, _, _, level, message ->
            "$level: $message"
        }
        test()
        file.delete()
    }
}
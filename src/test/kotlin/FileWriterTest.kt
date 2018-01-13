import com.serebit.loggerkt.FileWriter
import com.serebit.loggerkt.Logger
import io.kotlintest.TestCaseContext
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec
import java.io.File

class FileWriterTest : StringSpec() {
    private val fileName = "text.txt"
    private val file = File("${FileWriter::class.java.protectionDomain.codeSource.location.toURI().path}/$fileName")

    init {
        "File contents should equal input string" {
            Logger.info("My name jeff")
            file.readText().trim() shouldBe "INFO: My name jeff"
        }
    }

    override fun interceptTestCase(context: TestCaseContext, test: () -> Unit) {
        Logger.writer = FileWriter(fileName)
        Logger.format = { _, _, _, _, level, message ->
            "$level: $message"
        }
        test()
        file.delete()
    }
}
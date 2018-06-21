import com.serebit.loggerkt.LogMessage
import com.serebit.loggerkt.writers.FileWriter
import io.kotlintest.matchers.should
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec
import java.io.File

class FileWriterTest : StringSpec() {
    private val file = File("/tmp/test.tmp")

    init {
        "Writer should write messages to the set file" {
            val writer = FileWriter(file.absolutePath)
            writer.write(LogMessage.Simple("Test text"))
            file.readText() should { it.isNotEmpty() }
        }

        "Writer should write multiple messages to the same file" {
            val writer = FileWriter(file.absolutePath)
            writer.write(LogMessage.Simple("Test string"))
            writer.write(LogMessage.Simple("Second test string"))
            file.readText() shouldBe "Test string\nSecond test string\n"
        }

        "Writer should overwrite the output file if set as such" {
            var writer = FileWriter(file.absolutePath)
            writer.write(LogMessage.Simple("Old test string"))
            writer = FileWriter(file.absolutePath, overwrite = true)
            writer.write(LogMessage.Simple("New test string"))
            file.readText() shouldBe "New test string\n"
        }

        "Writer should append to the output file if set as such" {
            var writer = FileWriter(file.absolutePath)
            writer.write(LogMessage.Simple("Old test string"))
            writer = FileWriter(file.absolutePath, overwrite = false)
            writer.write(LogMessage.Simple("New test string"))
            file.readText() shouldBe "Old test string\nNew test string\n"
        }
    }
}

import com.serebit.loggerkt.writers.FileWriter
import io.kotlintest.should
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import java.io.File

class FileWriterTest : StringSpec({
    val file = File("/tmp/test.tmp")

    "Writer should write messages to the set file" {
        val writer = FileWriter(file.absolutePath)
        writer.write("Test text")
        file.readText() should { it.isNotEmpty() }
    }

    "Writer should write multiple messages to the same file" {
        val writer = FileWriter(file.absolutePath)
        writer.write("Test string")
        writer.write("Second test string")
        file.readText() shouldBe "Test string\nSecond test string\n"
    }

    "Writer should overwrite the output file if set as such" {
        var writer = FileWriter(file.absolutePath)
        writer.write("Old test string")
        writer = FileWriter(file.absolutePath, overwrite = true)
        writer.write("New test string")
        file.readText() shouldBe "New test string\n"
    }

    "Writer should append to the output file if set as such" {
        var writer = FileWriter(file.absolutePath)
        writer.write("Old test string")
        writer = FileWriter(file.absolutePath, overwrite = false)
        writer.write("New test string")
        file.readText() shouldBe "Old test string\nNew test string\n"
    }
})

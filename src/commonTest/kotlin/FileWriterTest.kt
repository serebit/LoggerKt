import com.serebit.logkat.LogLevel
import com.serebit.logkat.platform.File
import com.serebit.logkat.writers.FileWriter
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FileWriterTest {
    private val file = File.createTempFile("test")

    @Test
    fun `should write to the set file`() {
        val writer = FileWriter(file.absolutePath)
        writer.write("Test text", LogLevel.FATAL)
        assertTrue { file.readText().isNotBlank() }
    }

    @Test
    fun `should write the correct text to the set file`() {
        val writer = FileWriter(file.absolutePath)
        writer.write("Test text", LogLevel.FATAL)
        assertEquals(file.readText(), "Test text\n")
    }

    @Test
    fun `should write multiple messages to the same file`() {
        val writer = FileWriter(file.absolutePath)
        writer.write("Test string", LogLevel.FATAL)
        writer.write("Second test string", LogLevel.FATAL)
        assertEquals(file.readText(), "Test string\nSecond test string\n")
    }

    @Test
    fun `should overwrite the output file if set as such`() {
        var writer = FileWriter(file.absolutePath)
        writer.write("Old test string", LogLevel.FATAL)
        writer = FileWriter(file.absolutePath, overwrite = true)
        writer.write("New test string", LogLevel.FATAL)
        assertEquals(file.readText(), "New test string\n")
    }

    @Test
    fun `should append to the output file if set as such`() {
        var writer = FileWriter(file.absolutePath)
        writer.write("Old test string", LogLevel.FATAL)
        writer = FileWriter(file.absolutePath, overwrite = false)
        writer.write("New test string", LogLevel.FATAL)
        assertEquals(file.readText(), "Old test string\nNew test string\n")
    }

    @BeforeTest
    fun clearFile() {
        file.delete()
    }
}

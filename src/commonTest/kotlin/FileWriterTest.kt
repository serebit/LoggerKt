
import com.serebit.logkat.LogLevel
import com.serebit.logkat.internal.File
import com.serebit.logkat.internal.Platform
import com.serebit.logkat.writers.FileWriter
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FileWriterTest {
    private val file = File.createTempFile("test")
    private val ls = Platform.lineSeparator

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
        assertEquals("Test text$ls", file.readText())
    }

    @Test
    fun `should write multiple messages to the same file`() {
        val writer = FileWriter(file.absolutePath)
        writer.write("Test string", LogLevel.FATAL)
        writer.write("Second test string", LogLevel.FATAL)
        assertEquals("Test string${ls}Second test string$ls", file.readText())
    }

    @Test
    fun `should overwrite the output file if set as such`() {
        var writer = FileWriter(file.absolutePath)
        writer.write("Old test string", LogLevel.FATAL)
        writer = FileWriter(file.absolutePath, overwrite = true)
        writer.write("New test string", LogLevel.FATAL)
        assertEquals("New test string$ls", file.readText())
    }

    @Test
    fun `should append to the output file if set as such`() {
        var writer = FileWriter(file.absolutePath)
        writer.write("Old test string", LogLevel.FATAL)
        writer = FileWriter(file.absolutePath, overwrite = false)
        writer.write("New test string", LogLevel.FATAL)
        assertEquals("Old test string${ls}New test string${ls}", file.readText())
    }

    @BeforeTest
    fun clearFile() {
        file.delete()
    }
}

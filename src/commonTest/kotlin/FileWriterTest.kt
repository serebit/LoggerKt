import com.serebit.logkat.LogLevel
import com.serebit.logkat.platform.File
import com.serebit.logkat.writers.FileWriter
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FileWriterTest {
    val file = File("/tmp/test.tmp")

    @Test
    fun `writer should write messages to the set file`() {
        val writer = FileWriter(file.absolutePath, true)
        writer.write("Test text", LogLevel.FATAL)
        assertTrue { file.readText().isNotBlank() }
    }

    @Test
    fun `writer should write multiple messages to the same file`() {
        val writer = FileWriter(file.absolutePath, true)
        writer.write("Test string", LogLevel.FATAL)
        writer.write("Second test string", LogLevel.FATAL)
        assertEquals(file.readText(), "Test string\nSecond test string\n")
    }

    @Test
    fun `writer should overwrite the output file if set as such`() {
        var writer = FileWriter(file.absolutePath, true)
        writer.write("Old test string", LogLevel.FATAL)
        writer = FileWriter(file.absolutePath, overwrite = true)
        writer.write("New test string", LogLevel.FATAL)
        assertEquals(file.readText(), "New test string\n")
    }

    @Test
    fun `writer should append to the output file if set as such`() {
        var writer = FileWriter(file.absolutePath, true)
        writer.write("Old test string", LogLevel.FATAL)
        writer = FileWriter(file.absolutePath, overwrite = false)
        writer.write("New test string", LogLevel.FATAL)
        assertEquals(file.readText(), "Old test string\nNew test string\n")
    }
}

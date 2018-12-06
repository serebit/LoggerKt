
import com.serebit.logkat.platform.File
import kotlin.test.Test
import kotlin.test.assertEquals

class FileTest {
    @Test
    fun shouldMatchPath() {
        val file = File("/tmp/test.tmp")
        assertEquals(file.absolutePath, "/tmp/test.tmp")
    }
}

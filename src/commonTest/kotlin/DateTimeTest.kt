import com.serebit.logkat.time.DateTime
import kotlin.test.Test
import kotlin.test.assertEquals

class DateTimeTest {
    @Test
    fun `ensure correct values`() {
        with(DateTime(2018, 12, 364, 30, 7, 18, 49, 16, 483948593)) {
            assertEquals(2018, year.toInt())
            assertEquals(12, month.toInt())
            assertEquals("December", monthName)
            assertEquals(364, yearDay.toInt())
            assertEquals(30, day.toInt())
            assertEquals("Sunday", weekDay)
            assertEquals(18, hour.toInt())
            assertEquals(49, minute.toInt())
            assertEquals(16, second.toInt())
            assertEquals(483, millisecond.toInt())
        }
    }
    
    @Test
    fun `ensure correct padding when not necessary`() {
        with(DateTime(2018, 12, 364, 30, 7, 18, 49, 16, 483948593)) {
            assertEquals("2018", year)
            assertEquals("12", month)
            assertEquals("364", yearDay)
            assertEquals("30", day)
            assertEquals("18", hour)
            assertEquals("49", minute)
            assertEquals("16", second)
            assertEquals("483", millisecond)
        }
    }
    
    @Test
    fun `ensure correct padding when necessary`() {
        with(DateTime(2018, 1, 1, 1, 1, 0, 0, 0, 0)) {
            assertEquals("01", month)
            assertEquals("001", yearDay)
            assertEquals("01", day)
            assertEquals("00", hour)
            assertEquals("00", minute)
            assertEquals("00", second)
            assertEquals("000", millisecond)
        }
        
    }
}


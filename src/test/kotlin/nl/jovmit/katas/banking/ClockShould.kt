package nl.jovmit.katas.banking

import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDate

class ClockShould {

    @Test
    fun return_todays_date_in_dd_MM_yyyy_format() {
        val clock = TestableClock()

        val date = clock.todayAsString()

        assertEquals("24/04/2015", date)
    }

    inner class TestableClock : Clock() {

        override fun today(): LocalDate {
            return LocalDate.of(2015, 4, 24)
        }
    }
}
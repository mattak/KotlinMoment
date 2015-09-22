package me.mattak.moment

import org.junit.Test
import org.junit.Assert.*
import java.util.*

/**
 * MomentTest.kt
 * Created by mattak on 15/09/22.
 */
public class MomentTest {
    @Test
    fun init() {
        val defaultDate = Date()
        val defaultTimeZone = TimeZone.getDefault()
        val defaultLocale = Locale.getDefault()
        val m = Moment()
        assertTrue(m.date.after(defaultDate) || m.date.equals(defaultDate))
        assertTrue(m.timeZone.equals(defaultTimeZone))
        assertTrue(m.locale.equals(defaultLocale))
    }

    @Test
    fun year() {
        val calendar = Calendar.getInstance()
        calendar.set(2015, 1, 1, 0, 0, 0)
        val date = calendar.time
        val m = Moment(date)

        assertEquals(m.year, calendar.get(Calendar.YEAR))
    }

    @Test
    fun month() {
        val calendar = Calendar.getInstance()
        calendar.set(2015, 1, 1, 0, 0, 0)
        val date = calendar.time
        val m = Moment(date)

        assertEquals(m.month, calendar.get(Calendar.MONTH) + 1)
    }

    @Test
    fun day() {
        val calendar = Calendar.getInstance()
        calendar.set(2015, 1, 1, 0, 0, 0)
        val date = calendar.time
        val m = Moment(date)

        assertEquals(m.day, calendar.get(Calendar.DAY_OF_MONTH))
    }

    @Test
    fun hour() {
        val calendar = Calendar.getInstance()
        calendar.set(2015, 1, 1, 23, 59, 59)
        val date = calendar.time
        val m = Moment(date)

        assertEquals(m.hour, calendar.get(Calendar.HOUR_OF_DAY))
    }

    @Test
    fun minute() {
        val calendar = Calendar.getInstance()
        calendar.set(2015, 1, 1, 0, 0, 0)
        val date = calendar.time
        val m = Moment(date)

        assertEquals(m.minute, calendar.get(Calendar.MINUTE))
    }

    @Test
    fun second() {
        val calendar = Calendar.getInstance()
        calendar.set(2015, 1, 1, 0, 0, 0)
        val date = calendar.time
        val m = Moment(date)

        assertEquals(m.second, calendar.get(Calendar.SECOND))
    }

    @Test
    fun millisecond() {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = 1442922588569
        val date = calendar.time
        val m = Moment(date)

        assertEquals(m.millisecond, calendar.get(Calendar.MILLISECOND))
    }

    @Test
    fun compareTo() {
        val calendar = Calendar.getInstance()

        calendar.set(2015, 1, 1, 0, 0, 0)
        val date1 = calendar.time
        calendar.set(2015, 1, 2, 0, 0, 0)
        val date2 = calendar.time

        val m1 = Moment(date1)
        val m2 = Moment(date2)

        assertTrue(m2.compareTo(m2) == 0)
        assertTrue(m2.compareTo(m1) > 0)
        assertTrue(m1.compareTo(m2) < 0)
    }
}

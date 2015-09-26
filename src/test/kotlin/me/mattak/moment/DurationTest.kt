package me.mattak.moment

import org.junit.Test
import java.util.*
import kotlin.test.*

/**
 * DurationTest.kt
 * Created by mattak on 15/09/02.
 */
public class DurationTest {
    @Test
    fun seconds() {
        assertEquals(1, createDate(second = 1).seconds.toInt())
        assertEquals(59, createDate(second = 59).seconds.toInt())
        assertEquals(60, createDate(second = 60).seconds.toInt())
        assertEquals(120, createDate(second = 120).seconds.toInt())
    }

    @Test
    fun minutes() {
        assertEquals(1, createDate(minute = 1).minutes.toInt())
        assertEquals(0, createDate(second = 59).minutes.toInt())
        assertEquals(1, createDate(second = 60).minutes.toInt())
        assertEquals(1, createDate(second = 119).minutes.toInt())
        assertEquals(2, createDate(second = 120).minutes.toInt())
    }

    @Test
    fun hours() {
        assertEquals(1, createDate(hour = 1).hours.toInt())
        assertEquals(0, createDate(minute = 59).hours.toInt())
        assertEquals(1, createDate(minute = 60).hours.toInt())
        assertEquals(1, createDate(minute = 119).hours.toInt())
        assertEquals(2, createDate(minute = 120).hours.toInt())
    }

    @Test
    fun days() {
        assertEquals(1, createDate(day = 2).days.toInt())
        assertEquals(0, createDate(hour = 23).days.toInt())
        assertEquals(1, createDate(hour = 24).days.toInt())
        assertEquals(1, createDate(hour = 47).days.toInt())
        assertEquals(2, createDate(hour = 48).days.toInt())
    }

    @Test
    fun months() {
        assertEquals(1, createDate(month = 1).months.toInt())
        assertEquals(0, createDate(day = 30).months.toInt()) // calendar day begin from 1, but calculations is from 30 days.
        assertEquals(1, createDate(day = 31).months.toInt())
        assertEquals(1, createDate(day = 60).months.toInt())
        assertEquals(2, createDate(day = 61).months.toInt())
    }

    @Test
    fun quarters() {
        assertEquals(1, createDate(month = 3).quarters.toInt())
        assertEquals(0, createDate(month = 2).quarters.toInt())
        assertEquals(1, createDate(month = 3).quarters.toInt())
        assertEquals(1, createDate(month = 5).quarters.toInt())
        assertEquals(2, createDate(month = 6).quarters.toInt())
    }

    @Test
    fun years() {
        assertEquals(1, createDate(year = 1971).years.toInt())
        assertEquals(0, createDate(month = 11).years.toInt())
        assertEquals(1, createDate(month = 12).years.toInt())
        assertEquals(1, createDate(month = 23).years.toInt())
        assertEquals(2, createDate(month = 24).years.toInt())
    }

    @Test
    fun ago() {
        val now = Moment()
        val duration = Duration(1000)
        val past = duration.ago()
        assertTrue(now.date.after(past.date))
    }

    @Test
    fun add() {
        val d1 = Duration(1)
        val d2 = Duration(2)
        val d3 = Duration(-3)
        assertEquals(3.0, d1.add(d2).millisec)
        assertEquals(-2.0, d1.add(d3).millisec)
    }

    @Test
    fun subtract() {
        val d1 = Duration(1)
        val d2 = Duration(2)
        val d3 = Duration(-3)
        assertEquals(-1.0, d1.subtract(d2).millisec)
        assertEquals(4.0, d1.subtract(d3).millisec)
    }

    @Test
    fun equals() {
        val d1 = Duration(1)
        val d2 = Duration(1)
        val d3 = Duration(2)
        assertTrue(d1.equals(d1))
        assertTrue(d1.equals(d2))
        assertFalse(d1.equals(d3))
        assertFalse(d1.equals(null))
        assertFalse(d1.equals("something other object"))
    }

    @Test
    fun _toString() {
        // TODO: write
    }

    @Test
    fun compareTo() {
        val d1 = Duration(1)
        val d2 = Duration(-100)
        val d3 = Duration(10000)
        assertTrue(d1.compareTo(d1) == 0)
        assertTrue(d1.compareTo(d2) > 0)
        assertTrue(d1.compareTo(d3) < 0)
    }

    private fun createDate(
            year: Int = 1970,
            month: Int = 0,
            day: Int = 1,
            hour: Int = 0,
            minute: Int = 0,
            second: Int = 0
    ): Duration {
        val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
        calendar.set(year, month, day, hour, minute, second)
        return Duration(calendar.getTime().getTime())
    }
}
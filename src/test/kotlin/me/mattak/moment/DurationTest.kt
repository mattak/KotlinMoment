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
    fun millisec() {
        assertEquals(1, Duration(1).millisec.toInt())
    }

    @Test
    fun millisecFloor() {
        assertEquals(1, Duration(1).millisecFloor.toInt())
        assertEquals(1001, Duration(1001).millisecFloor.toInt())
    }

    @Test
    fun millisecFloorUnit() {
        assertEquals(1, Duration(1).millisecFloorUnit.toInt())
        assertEquals(1, Duration(1001).millisecFloorUnit.toInt())
    }

    @Test
    fun seconds() {
        assertEquals(1, createDate(second = 1).seconds.toInt())
        assertEquals(59, createDate(second = 59).seconds.toInt())
        assertEquals(60, createDate(second = 60).seconds.toInt())
        assertEquals(120, createDate(second = 120).seconds.toInt())
    }

    @Test
    fun secondsFloor() {
        assertEquals(1, createDate(second = 1).secondsFloor.toInt())
        assertEquals(61, createDate(second = 61).secondsFloor.toInt())
    }

    @Test
    fun secondsFloorUnit() {
        assertEquals(1, createDate(second = 1).secondsFloorUnit.toInt())
        assertEquals(1, createDate(second = 61).secondsFloorUnit.toInt())
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
    fun minutesFloor() {
        assertEquals(1, createDate(minute = 1).minutesFloor.toInt())
        assertEquals(60, createDate(minute = 60).minutesFloor.toInt())
    }

    @Test
    fun minutesFloorUnit() {
        assertEquals(1, createDate(minute = 1).minutesFloorUnit.toInt())
        assertEquals(0, createDate(minute = 60).minutesFloorUnit.toInt())
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
    fun hoursFloor() {
        assertEquals(1, createDate(hour = 1).hoursFloor.toInt())
        assertEquals(24, createDate(hour = 24).hoursFloor.toInt())
    }

    @Test
    fun hoursFloorUnit() {
        assertEquals(1, createDate(hour = 1).hoursFloorUnit.toInt())
        assertEquals(0, createDate(hour = 0).hoursFloorUnit.toInt())
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
    fun daysFloor() {
        assertEquals(0, createDate(day = 1).daysFloor.toInt())
        assertEquals(30, createDate(day = 31).daysFloor.toInt())
    }

    @Test
    fun daysFloorUnit() {
        assertEquals(0, createDate(day = 1).daysFloorUnit.toInt())
        assertEquals(0, createDate(day = 31).daysFloorUnit.toInt())
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
    fun monthsFloor() {
        assertEquals(0, createDate(month = 0).monthsFloor.toInt())
        assertEquals(12, createDate(month = 12).monthsFloor.toInt())
    }

    @Test
    fun monthsFloorUnit() {
        assertEquals(0, createDate(month = 0).monthsFloorUnit.toInt())
        assertEquals(11, createDate(month = 11).monthsFloorUnit.toInt())
        assertEquals(0, createDate(month = 12).monthsFloorUnit.toInt())
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
    fun quartersFloor() {
        assertEquals(0, createDate(month = 0).quartersFloor.toInt())
        assertEquals(4, createDate(month = 12).quartersFloor.toInt())
    }

    @Test
    fun quartersFloorUnit() {
        assertEquals(0, createDate(month = 0).quartersFloorUnit.toInt())
        assertEquals(0, createDate(month = 12).quartersFloorUnit.toInt())
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
    fun yearsFloor() {
        assertEquals(1, createDate(year = 1971).yearsFloor.toInt())
    }

    @Test
    fun yearsFloorUnit() {
        assertEquals(1, createDate(year = 1971).yearsFloorUnit.toInt())
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
        val d = Duration(1)
        assertEquals("1", d.toString())
    }

    @Test
    fun toNSDateFormatString() {
        assertEquals("0", Duration(0).toNSDateFormatString())
        assertEquals("1", Duration(1000).toNSDateFormatString())
        assertEquals("59", Duration(59000).toNSDateFormatString())
        assertEquals("1:00", Duration(60000).toNSDateFormatString())
        assertEquals("59:59",   Duration(3599000).toNSDateFormatString())
        assertEquals("1:00:00", Duration(3600000).toNSDateFormatString())
        assertEquals("23:59:59", Duration(86399999).toNSDateFormatString())
        assertEquals("1d 00:00:00", Duration(86400000).toNSDateFormatString())
        assertEquals("29d 23:59:59", Duration(2591999999).toNSDateFormatString())
        assertEquals("1m 0d 00:00:00", Duration(2678400000).toNSDateFormatString())
        assertEquals("11m 30d 23:59:59", Duration(31535999999).toNSDateFormatString())
        assertEquals("1y 0m 0d 00:00:00", Duration(31536000000).toNSDateFormatString())
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
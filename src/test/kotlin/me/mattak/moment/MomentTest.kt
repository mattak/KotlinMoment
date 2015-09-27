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
        val m1 = Moment()
        assertTrue(m1.date.after(defaultDate) || m1.date.equals(defaultDate))
        assertTrue(m1.timeZone.equals(defaultTimeZone))
        assertTrue(m1.locale.equals(defaultLocale))

        val m2 = Moment(defaultDate, defaultTimeZone, defaultLocale)
        assertTrue(m2.date.after(defaultDate) || m2.date.equals(defaultDate))
        assertTrue(m2.timeZone.equals(defaultTimeZone))
        assertTrue(m2.locale.equals(defaultLocale))
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
    fun weekday() {
        val calendar = Calendar.getInstance()
        calendar.set(2015, 1, 1, 0, 0, 0)
        val date = calendar.time
        val m = Moment(date)

        assertEquals(m.weekday, calendar.get(Calendar.DAY_OF_WEEK))
    }

    @Test
    fun weekdayName() {
        val calendar = Calendar.getInstance(Locale.ENGLISH)
        calendar.set(2015, 1, 1, 0, 0, 0)
        val date = calendar.time
        val m = Moment(date, locale = Locale.ENGLISH)

        assertEquals(m.weekdayName, "Sunday")
    }

    @Test
    fun weekdayOrdinal() {
        val calendar = Calendar.getInstance()
        calendar.set(2015, 1, 1, 0, 0, 0)
        val date = calendar.time
        val m = Moment(date)

        assertEquals(m.weekdayOrdinal, calendar.get(Calendar.WEEK_OF_MONTH))
    }

    @Test
    fun weekdayOfYear() {
        val calendar = Calendar.getInstance()
        calendar.set(2015, 1, 1, 0, 0, 0)
        val date = calendar.time
        val m = Moment(date)

        assertEquals(m.weekOfYear, calendar.get(Calendar.WEEK_OF_YEAR))
    }

    @Test
    fun quarter() {
        val calendar = Calendar.getInstance()
        calendar.set(2015, 1, 1, 0, 0, 0)
        val date = calendar.time
        val m = Moment(date)

        assertEquals(m.quarter, 1)
    }

    @Test
    fun epoch() {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = 123456789
        val date = calendar.time
        val m = Moment(date)

        assertEquals(m.epoch, 123456)
    }

    @Test
    fun get() {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = 123456789
        val m1 = Moment(calendar.time)
        assertEquals(m1.get(TimeUnit.MILLISECONDS), 789)

        calendar.set(2015, 0, 1, 0, 0, 0)
        val m2 = Moment(calendar.time)
        assertEquals(0, m2.get(TimeUnit.SECONDS))
        assertEquals(0, m2.get(TimeUnit.MINUTES))
        assertEquals(0, m2.get(TimeUnit.HOURS))
        assertEquals(1, m2.get(TimeUnit.DAYS))
        assertEquals(1, m2.get(TimeUnit.MONTHS))
        assertEquals(1, m2.get(TimeUnit.QUARTERS))
        assertEquals(2015, m2.get(TimeUnit.YEARS))
    }

    @Test
    fun format() {
        val calendar = Calendar.getInstance()
        calendar.set(2015, 0, 1, 0, 0, 0)
        val m = Moment(calendar.time)
        assertEquals("2015-01-01 00:00:00", m.format("yyyy-MM-dd HH:mm:ss"))
    }

    @Test
    fun add() {
        val calendar = Calendar.getInstance()
        calendar.set(2015, 0, 1, 0, 0, 0)
        var m = Moment(calendar.time)
        assertEquals("2015-01-01 00:00:00", m.format("yyyy-MM-dd HH:mm:ss"))
        m = m.add(1, TimeUnit.SECONDS)
        assertEquals("2015-01-01 00:00:01", m.format("yyyy-MM-dd HH:mm:ss"))
        m = m.add(1, TimeUnit.MINUTES)
        assertEquals("2015-01-01 00:01:01", m.format("yyyy-MM-dd HH:mm:ss"))
        m = m.add(1, TimeUnit.HOURS)
        assertEquals("2015-01-01 01:01:01", m.format("yyyy-MM-dd HH:mm:ss"))
        m = m.add(1, TimeUnit.DAYS)
        assertEquals("2015-01-02 01:01:01", m.format("yyyy-MM-dd HH:mm:ss"))
        m = m.add(1, TimeUnit.MONTHS)
        assertEquals("2015-02-02 01:01:01", m.format("yyyy-MM-dd HH:mm:ss"))
        m = m.add(1, TimeUnit.QUARTERS)
        assertEquals("2015-05-02 01:01:01", m.format("yyyy-MM-dd HH:mm:ss"))
        m = m.add(1, TimeUnit.YEARS)
        assertEquals("2016-05-02 01:01:01", m.format("yyyy-MM-dd HH:mm:ss"))
    }

    @Test
    fun add_duration() {
        val calendar = Calendar.getInstance()
        calendar.set(2015, 0, 1, 0, 0, 0)
        var m = Moment(calendar.time)
        assertEquals("2015-01-01 00:00:00", m.format("yyyy-MM-dd HH:mm:ss"))
        m = m.add(Duration(1000))
        assertEquals("2015-01-01 00:00:01", m.format("yyyy-MM-dd HH:mm:ss"))
    }

    @Test
    fun subtract() {
        val calendar = Calendar.getInstance()
        calendar.set(2015, 0, 1, 0, 0, 1)
        var m = Moment(calendar.time)
        assertEquals("2015-01-01 00:00:01", m.format("yyyy-MM-dd HH:mm:ss"))
        m = m.subtract(1, TimeUnit.SECONDS)
        assertEquals("2015-01-01 00:00:00", m.format("yyyy-MM-dd HH:mm:ss"))
    }

    @Test
    fun subtract_duration() {
        val calendar = Calendar.getInstance()
        calendar.set(2015, 0, 1, 0, 0, 1)
        var m = Moment(calendar.time)
        assertEquals("2015-01-01 00:00:01", m.format("yyyy-MM-dd HH:mm:ss"))
        m = m.subtract(Duration(1000))
        assertEquals("2015-01-01 00:00:00", m.format("yyyy-MM-dd HH:mm:ss"))
    }

    @Test
    fun isCloseTo() {
        val calendar = Calendar.getInstance()
        calendar.set(2015, 0, 1, 0, 0, 1)
        var m0 = Moment(calendar.time)
        calendar.set(2015, 0, 1, 0, 1, 0)
        var m1 = Moment(calendar.time)
        assertTrue(m0.isCloseTo(m1, 60000))
        assertTrue(m0.isCloseTo(m1, 59999))
    }

    @Test
    fun startOf() {
        val calendar = Calendar.getInstance()
        calendar.set(2015, 11, 31, 23, 59, 59)
        var m = Moment(calendar.time)
        assertEquals("2015-12-31 23:59:59", m.format("yyyy-MM-dd HH:mm:ss"))
        assertEquals("2015-12-31 23:59:59", m.startOf(TimeUnit.MILLISECONDS).format("yyyy-MM-dd HH:mm:ss"))
        assertEquals("2015-12-31 23:59:59", m.startOf(TimeUnit.SECONDS).format("yyyy-MM-dd HH:mm:ss"))
        assertEquals("2015-12-31 23:59:00", m.startOf(TimeUnit.MINUTES).format("yyyy-MM-dd HH:mm:ss"))
        assertEquals("2015-12-31 23:00:00", m.startOf(TimeUnit.HOURS).format("yyyy-MM-dd HH:mm:ss"))
        assertEquals("2015-12-31 00:00:00", m.startOf(TimeUnit.DAYS).format("yyyy-MM-dd HH:mm:ss"))
        assertEquals("2015-12-01 00:00:00", m.startOf(TimeUnit.MONTHS).format("yyyy-MM-dd HH:mm:ss"))
        assertEquals("2015-10-01 00:00:00", m.startOf(TimeUnit.QUARTERS).format("yyyy-MM-dd HH:mm:ss"))
        assertEquals("2015-01-01 00:00:00", m.startOf(TimeUnit.YEARS).format("yyyy-MM-dd HH:mm:ss"))
    }

    @Test
    fun endOf() {
        val calendar = Calendar.getInstance()
        calendar.set(2015, 0, 1, 0, 0, 0)
        var m = Moment(calendar.time)
        assertEquals("2015-01-01 00:00:00", m.format("yyyy-MM-dd HH:mm:ss"))
        assertEquals("2015-01-01 00:00:00", m.endOf(TimeUnit.MILLISECONDS).format("yyyy-MM-dd HH:mm:ss"))
        assertEquals("2015-01-01 00:00:00", m.endOf(TimeUnit.SECONDS).format("yyyy-MM-dd HH:mm:ss"))
        assertEquals("2015-01-01 00:00:59", m.endOf(TimeUnit.MINUTES).format("yyyy-MM-dd HH:mm:ss"))
        assertEquals("2015-01-01 00:59:59", m.endOf(TimeUnit.HOURS).format("yyyy-MM-dd HH:mm:ss"))
        assertEquals("2015-01-01 23:59:59", m.endOf(TimeUnit.DAYS).format("yyyy-MM-dd HH:mm:ss"))
        assertEquals("2015-01-31 23:59:59", m.endOf(TimeUnit.MONTHS).format("yyyy-MM-dd HH:mm:ss"))
        assertEquals("2015-12-31 23:59:59", m.endOf(TimeUnit.YEARS).format("yyyy-MM-dd HH:mm:ss"))
    }

    @Test
    fun intervalSince() {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = 1442922588000
        val date1 = calendar.time
        calendar.timeInMillis = 1442922588001
        val date2 = calendar.time

        val m1 = Moment(date1)
        val m2 = Moment(date2)

        assertEquals(0, m1.intervalSince(m1).interval)
        assertEquals(1, m2.intervalSince(m1).interval)
        assertEquals(-1, m1.intervalSince(m2).interval)
    }


    @Test
    fun equals() {
        val calendar = Calendar.getInstance()

        calendar.set(2015, 1, 1, 0, 0, 0)
        val date1 = calendar.time
        val date2 = calendar.time
        calendar.set(2015, 1, 2, 0, 0, 0)
        val date3 = calendar.time

        val m1 = Moment(date1)
        val m2 = Moment(date2)
        val m3 = Moment(date3)

        assertTrue(m1.equals(m1))
        assertTrue(m1.equals(m2))
        assertFalse(m1.equals(m3))
        assertFalse(m1.equals("hoge"))
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

    @Test
    fun _toString() {
        val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
        calendar.set(2015, 0, 1, 0, 0, 0)
        assertEquals("2015-01-01 00:00:00 +0000", Moment(calendar.time, TimeZone.getTimeZone("UTC")).toString())
    }
}
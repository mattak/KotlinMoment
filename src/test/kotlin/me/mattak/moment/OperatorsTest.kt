package me.mattak.moment

import org.junit.Test
import java.util.*
import kotlin.test.assertEquals

/**
 * Test for Operators
 * Created by mattak on 15/09/27.
 */
public class OperatorsTest {
    @Test
    fun moment_minus_moment() {
        val m1 = Moment()
        val m2 = m1.add(Duration(1))
        assertEquals(1, (m2 - m1).interval.toInt())
    }

    @Test
    fun moment_plus_duration() {
        val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
        calendar.set(2015, 0, 1, 0, 0, 0)
        val m1 = Moment(calendar.time, calendar.timeZone)
        val m2 = m1 + Duration(1000)
        assertEquals("2015-01-01 00:00:01", m2.format("yyyy-MM-dd HH:mm:ss"))
    }

    @Test
    fun moment_minus_duration() {
        val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
        calendar.set(2015, 0, 1, 0, 0, 1)
        val m1 = Moment(calendar.time, calendar.timeZone)
        val m2 = m1 - Duration(1000)
        assertEquals("2015-01-01 00:00:00", m2.format("yyyy-MM-dd HH:mm:ss"))
    }

    @Test
    fun duration_plus_moment() {
        val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
        calendar.set(2015, 0, 1, 0, 0, 0)
        val m1 = Moment(calendar.time, calendar.timeZone)
        val m2 = Duration(1000) + m1
        assertEquals("2015-01-01 00:00:01", m2.format("yyyy-MM-dd HH:mm:ss"))
    }

    @Test
    fun duration_minus_moment() {
        val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
        calendar.set(2015, 0, 1, 0, 0, 1)
        val m1 = Moment(calendar.time, calendar.timeZone)
        val m2 = Duration(1000) - m1
        assertEquals("2015-01-01 00:00:00", m2.format("yyyy-MM-dd HH:mm:ss"))
    }

    @Test
    fun duration_plus_duration() {
        val d = Duration(1) + Duration(2)
        assertEquals(3, d.interval.toInt())
    }

    @Test
    fun duration_minus_duration() {
        val d = Duration(2) - Duration(1)
        assertEquals(1, d.interval.toInt())
    }
}
package me.mattak.moment

import org.junit.Test
import java.util.*
import kotlin.test.assertEquals

/**
 * Moment Extensions Test
 * Created by mattak on 15/09/29.
 */
public class MomentExtensionsTest {
    @Test
    fun _utc() {
        val moment = utc()
        assertEquals(TimeZone.getTimeZone("UTC"), moment.timeZone)
    }

    @Test
    fun _moment_stringDate() {
        val m1 = moment("2015-01-01T00:00:00+0900")!!
        assertEquals("2015-01-01 00:00:00", m1.format("yyyy-MM-dd HH:mm:ss"))

        val m2 = moment("2015-01-01 00:00:00+0900")!!
        assertEquals("2015-01-01 00:00:00", m2.format("yyyy-MM-dd HH:mm:ss"))

        val m3 = moment("2015-01-01T00:00:00.000+0900")!!
        assertEquals("2015-01-01 00:00:00", m3.format("yyyy-MM-dd HH:mm:ss"))

        val m4 = moment("2015-01-01 00:00:00.000+0900")!!
        assertEquals("2015-01-01 00:00:00", m4.format("yyyy-MM-dd HH:mm:ss"))

        val m5 = moment("2015-01-01T00:00:00")!!
        assertEquals("2015-01-01 00:00:00", m5.format("yyyy-MM-dd HH:mm:ss"))

        val m6 = moment("2015-01-01 00:00:00")!!
        assertEquals("2015-01-01 00:00:00", m6.format("yyyy-MM-dd HH:mm:ss"))

        val m7 = moment("2015-01-01")!!
        assertEquals("2015-01-01", m7.format("yyyy-MM-dd"))

        val m8 = moment("1:23:45 PM", locale = Locale.ENGLISH)!!
        assertEquals("13:23:45", m8.format("HH:mm:ss"))

        val m9 = moment("1:23 PM", locale = Locale.ENGLISH)!!
        assertEquals("13:23", m9.format("HH:mm"))

        val m10 = moment("12/01/2015")!!
        assertEquals("2015-12-01", m10.format("yyyy-MM-dd"))

        val m11 = moment("June 1, 2015 1:23 PM", locale = Locale.ENGLISH)!!
        assertEquals("2015-06-01 13:23", m11.format("yyyy-MM-dd HH:mm"))

        val m12 = moment("June 1, 2015", locale = Locale.ENGLISH)!!
        assertEquals("2015-06-01", m12.format("yyyy-MM-dd"))

        val m14 = moment("002015-01-01", locale = Locale.ENGLISH)!!
        assertEquals("2015-01-01", m14.format("yyyy-MM-dd"))

        val m15 = moment("002015-01-01", locale = Locale.ENGLISH)!!
        assertEquals("2015-01-01", m15.format("yyyy-MM-dd"))

        val m16 = moment("2015-W01-Thu", locale = Locale.ENGLISH)!!
        assertEquals("2015-01-01", m16.format("yyyy-MM-dd"))

        val m17 = moment("2015-W02", locale = Locale.ENGLISH)!!
        assertEquals("2015-01-04", m17.format("yyyy-MM-dd"))

        val m18 = moment("2015-365", locale = Locale.ENGLISH)!!
        assertEquals("2015-12-31", m18.format("yyyy-MM-dd"))

        val m19 = moment("2015-365", locale = Locale.ENGLISH)!!
        assertEquals("2015-12-31", m19.format("yyyy-MM-dd"))

        val m20 = moment("12:00:00.012", locale = Locale.ENGLISH)!!
        assertEquals("12:00:00.012", m20.format("HH:mm:ss.SSS"))

        val m21 = moment("12:00:00", locale = Locale.ENGLISH)!!
        assertEquals("12:00:00", m21.format("HH:mm:ss"))

        val m22 = moment("12:00", locale = Locale.ENGLISH)!!
        assertEquals("12:00", m22.format("HH:mm"))

        val m23 = moment("23", locale = Locale.ENGLISH)!!
        assertEquals("23", m23.format("HH"))
    }

    @Test
    fun moment_stringDate_dateFormat() {
        val moment = moment("2015", "yyyy")!!
        assertEquals(2015, moment.year)
    }

    @Test
    fun moment_IntArray() {
        val moment = moment(intArrayOf(2015, 1, 1, 12, 34, 56))!!
        assertEquals(2015, moment.year)
        assertEquals(1, moment.month)
        assertEquals(1, moment.day)
        assertEquals(12, moment.hour)
        assertEquals(34, moment.minute)
        assertEquals(56, moment.second)
    }

    @Test
    fun moment_dictionary() {
        val moment = moment(mapOf(
                "year" to 2015,
                "month" to 1,
                "day" to 1,
                "hour" to 12,
                "minute" to 34,
                "second" to 56
        ))!!
        assertEquals(2015, moment.year)
        assertEquals(1, moment.month)
        assertEquals(1, moment.day)
        assertEquals(12, moment.hour)
        assertEquals(34, moment.minute)
        assertEquals(56, moment.second)
    }

    @Test
    fun moment_milliseconds() {
        val moment = moment(0, timeZone = TimeZone.getTimeZone("UTC"))
        assertEquals(1970, moment.year)
        assertEquals(1, moment.month)
        assertEquals(1, moment.day)
        assertEquals(0, moment.hour)
        assertEquals(0, moment.minute)
        assertEquals(0, moment.second)
        assertEquals(0, moment.millisecond)
    }
}

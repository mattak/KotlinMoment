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
        val m1 = moment("2015-01-01T00:00:00 +0900")!!
        assertEquals("2015-01-01 00:00:00", m1.format("yyyy-MM-dd HH:mm:ss"))

        val m2 = moment("2015-01-01 00:00:00 +0900")!!
        assertEquals("2015-01-01 00:00:00", m2.format("yyyy-MM-dd HH:mm:ss"))

        val m3 = moment("2015-01-01T00:00:00.000 +0900")!!
        assertEquals("2015-01-01 00:00:00", m3.format("yyyy-MM-dd HH:mm:ss"))

        val m4 = moment("2015-01-01 00:00:00.000 +0900")!!
        assertEquals("2015-01-01 00:00:00", m4.format("yyyy-MM-dd HH:mm:ss"))

        val m5 = moment("2015-01-01T00:00:00")!!
        assertEquals("2015-01-01 00:00:00", m5.format("yyyy-MM-dd HH:mm:ss"))

        val m6 = moment("2015-01-01 00:00:00")!!
        assertEquals("2015-01-01 00:00:00", m6.format("yyyy-MM-dd HH:mm:ss"))
    }
}

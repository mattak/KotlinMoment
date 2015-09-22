package me.mattak.moment

import org.junit
import java.util.*
import kotlin.test.*

/**
 * DurationTest.kt
 * Created by mattak on 15/09/02.
 */
public class DurationsTest {
    junit.Test
    fun second() {
        val calendar = Calendar.getInstance()
        assertEquals(1, createDate(second = 1).seconds.toInt())
        assertEquals(59, createDate(second = 59).seconds.toInt())
        assertEquals(60, createDate(second = 60).seconds.toInt())
        assertEquals(120, createDate(second = 120).seconds.toInt())
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
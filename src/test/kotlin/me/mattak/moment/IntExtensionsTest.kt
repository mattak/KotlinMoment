package me.mattak.moment

import org.junit.Test
import kotlin.test.assertEquals

/**
 * Tests for IntExtensions
 * Created by mattak on 15/09/27.
 */
public class IntExtensionsTest {
    @Test
    fun milliseconds() {
        assertEquals(1.toLong(), 1.milliseconds.interval)
    }

    @Test
    fun seconds() {
        assertEquals(TimeUnit.SECONDS.durationMultiply.toLong(), 1.seconds.interval)
    }

    @Test
    fun minutes() {
        assertEquals(TimeUnit.MINUTES.durationMultiply.toLong(), 1.minutes.interval)
    }

    @Test
    fun hours() {
        assertEquals(TimeUnit.HOURS.durationMultiply.toLong(), 1.hours.interval)
    }

    @Test
    fun days() {
        assertEquals(TimeUnit.DAYS.durationMultiply.toLong(), 1.days.interval)
    }

    @Test
    fun months() {
        assertEquals(TimeUnit.MONTHS.durationMultiply.toLong(), 1.months.interval)
    }

    @Test
    fun quarters() {
        assertEquals(TimeUnit.QUARTERS.durationMultiply.toLong(), 1.quarters.interval)
    }

    @Test
    fun years() {
        assertEquals(TimeUnit.YEARS.durationMultiply.toLong(), 1.years.interval)
    }
}
package me.mattak.moment

import java.text.SimpleDateFormat
import java.util.*

/**
 * Duration class is interval of milliseconds. The precision is very rough. (especially month and years)
 * Created by mattak on 15/09/02.
 */
public class Duration(val interval: Long) : Comparable<Duration> {
    val years: Double
        get() = interval.toDouble() / TimeUnit.YEARS.durationMultiply

    val yearsFloor: Long
        get() = interval / TimeUnit.YEARS.durationMultiply

    val yearsFloorUnit: Long
        get() = yearsFloor

    val quarters: Double
        get() = interval.toDouble() / TimeUnit.QUARTERS.durationMultiply

    val quartersFloor: Long
        get() = interval / TimeUnit.QUARTERS.durationMultiply

    val quartersFloorUnit: Long
        get() = quartersFloor % 4

    val months: Double
        get() = interval.toDouble() / TimeUnit.MONTHS.durationMultiply

    val monthsFloor: Long
        get() = interval / TimeUnit.MONTHS.durationMultiply

    val monthsFloorUnit: Long
        get() = monthsFloor % 12

    val days: Double
        get() = interval.toDouble() / TimeUnit.DAYS.durationMultiply

    val daysFloor: Long
        get() = interval / TimeUnit.DAYS.durationMultiply

    val daysFloorUnit: Long
        get() = daysFloor % 30

    val hours: Double
        get() = interval.toDouble() / TimeUnit.HOURS.durationMultiply

    val hoursFloor: Long
        get() = interval / TimeUnit.HOURS.durationMultiply

    val hoursFloorUnit: Long
        get() = hoursFloor % 24

    val minutes: Double
        get() = interval.toDouble() / TimeUnit.MINUTES.durationMultiply

    val minutesFloor: Long
        get() = interval / TimeUnit.MINUTES.durationMultiply

    val minutesFloorUnit: Long
        get() = minutesFloor % 60

    val seconds: Double
        get() = interval.toDouble() / TimeUnit.SECONDS.durationMultiply

    val secondsFloor: Long
        get() = interval / TimeUnit.SECONDS.durationMultiply

    val secondsFloorUnit: Long
        get() = secondsFloor % 60

    val millisec: Double
        get() = interval.toDouble()

    val millisecFloor: Long
        get() = interval

    val millisecFloorUnit: Long
        get() = interval % 1000

    fun ago(): Moment {
        return Moment().subtract(this)
    }

    fun add(duration: Duration): Duration {
        return Duration(this.interval + duration.interval)
    }

    fun subtract(duration: Duration): Duration {
        return Duration(this.interval - duration.interval)
    }

    override fun equals(other: Any?): Boolean {
        if (other != null && other is Duration) {
            return this.interval == other.interval
        }

        return false
    }

    /**
     * Duration interval.
     *
     * @return durations interval.
     */
    override fun toString(): String {
        return interval.toString()
    }

    private fun getDateFormatNumber(
            dateFormat: String,
            date: Date,
            locale: Locale = Locale.getDefault(),
            timeZone: TimeZone = TimeZone.getDefault()
    ): Int {
        return getSimpleDateFormat(dateFormat, locale, timeZone).format(date).toInt()
    }

    private fun getSimpleDateFormat(
            dateFormat: String,
            locale: Locale = Locale.getDefault(),
            timeZone: TimeZone = TimeZone.getDefault()
    ): SimpleDateFormat {
        val format = SimpleDateFormat(dateFormat, locale)
        format.timeZone = timeZone
        return format
    }

    override fun compareTo(other: Duration): Int {
        if (this.interval > other.interval) {
            return 1
        } else if (this.interval < other.interval) {
            return -1
        } else {
            return 0
        }
    }
}

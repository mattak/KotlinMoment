package me.mattak.moment

import java.text.SimpleDateFormat
import java.util.*

/**
 * Moment
 * Created by mattak on 15/09/02.
 */
public class Moment(
        val date: Date = Date(),
        val timeZone: TimeZone = TimeZone.getDefault(),
        val locale: Locale = Locale.getDefault()
) : Comparable<Moment> {
    val year: Int
        get() = getCalendarNumber(Calendar.YEAR)

    val month: Int
        get() = getCalendarNumber(Calendar.MONTH) + 1 // 0-11

    val day: Int
        get() = getCalendarNumber(Calendar.DAY_OF_MONTH)

    val hour: Int
        get() = getCalendarNumber(Calendar.HOUR_OF_DAY)

    val minute: Int
        get() = getCalendarNumber(Calendar.MINUTE)

    val second: Int
        get() = getCalendarNumber(Calendar.SECOND)

    val millisecond: Int
        get() = getCalendarNumber(Calendar.MILLISECOND)

    val weekday: Int
        get() = getCalendarNumber(Calendar.DAY_OF_WEEK)

    val weekdayName: String
        get() = getFormatString("EEEE")

    val weekdayOrdinal: Int
        get() = getCalendarNumber(Calendar.DAY_OF_WEEK)

    val weekOfYear: Int
        get() = getCalendarNumber(Calendar.WEEK_OF_YEAR)

    val quarter: Int
        get() = getCalendarNumber(Calendar.MONTH) / 3 + 1

    val epoch: Long
        get() = getCalendar().timeInMillis / 1000

    fun get(unit: TimeUnit): Int {
        when (unit) {
            TimeUnit.MILLISECONDS -> return millisecond
            TimeUnit.SECONDS -> return second
            TimeUnit.MINUTES -> return minute
            TimeUnit.HOURS -> return hour
            TimeUnit.DAYS -> return day
            TimeUnit.MONTHS -> return month
            TimeUnit.QUARTERS -> return quarter
            TimeUnit.YEARS -> return year
        }
    }

    fun format(dateFormat: String = "yyyy-MM-dd HH:mm:ss ZZZZ"): String {
        return getFormatString(dateFormat)
    }

    fun add(value: Long, unit: TimeUnit): Moment {
        val calendar = getCalendar()

        when (unit) {
            TimeUnit.MILLISECONDS -> {
                calendar.timeInMillis = calendar.timeInMillis + value
                return Moment(calendar.time, timeZone, locale)
            }
            TimeUnit.SECONDS -> {
                calendar.add(Calendar.SECOND, value.toInt())
                return Moment(calendar.time, timeZone, locale)
            }
            TimeUnit.MINUTES -> {
                calendar.add(Calendar.MINUTE, value.toInt())
                return Moment(calendar.time, timeZone, locale)
            }
            TimeUnit.HOURS -> {
                calendar.add(Calendar.HOUR_OF_DAY, value.toInt())
                return Moment(calendar.time, timeZone, locale)
            }
            TimeUnit.DAYS -> {
                calendar.add(Calendar.DAY_OF_YEAR, value.toInt())
                return Moment(calendar.time, timeZone, locale)
            }
            TimeUnit.MONTHS -> {
                calendar.add(Calendar.MONTH, value.toInt())
                return Moment(calendar.time, timeZone, locale)
            }
            TimeUnit.QUARTERS -> {
                calendar.add(Calendar.MONTH, value.toInt() * 3)
                return Moment(calendar.time, timeZone, locale)
            }
            TimeUnit.YEARS -> {
                calendar.add(Calendar.YEAR, value.toInt())
                return Moment(calendar.time, timeZone, locale)
            }
        }
    }

    fun add(value: Duration): Moment {
        return add(value.interval, TimeUnit.MILLISECONDS)
    }

    fun subtract(value: Long, unit: TimeUnit): Moment {
        return add(-value, unit)
    }

    fun subtract(duration: Duration): Moment {
        return add(-duration.interval, TimeUnit.MILLISECONDS)
    }

    fun isCloseTo(moment: Moment, precision: Long): Boolean {
        val delta = intervalSince(moment)
        return Math.abs(delta.millisec) <= precision
    }

    fun startOf(unit: TimeUnit): Moment {
        val calendar = getCalendar()

        if (unit.order == TimeUnit.MILLISECONDS.order) {
            return this
        }
        if (unit.order <= TimeUnit.YEARS.order) {
            calendar.set(Calendar.MONTH, 0)
        }
        if (unit.order <= TimeUnit.QUARTERS.order) {
            val month = calendar.get(Calendar.MONTH)
            val startMonth = (month / 3) * 3
            calendar.set(Calendar.MONTH, startMonth)
        }
        if (unit.order <= TimeUnit.MONTHS.order) {
            calendar.set(Calendar.DAY_OF_MONTH, 1)
        }
        if (unit.order <= TimeUnit.DAYS.order) {
            calendar.set(Calendar.HOUR_OF_DAY, 0)
        }
        if (unit.order <= TimeUnit.HOURS.order) {
            calendar.set(Calendar.MINUTE, 0)
        }
        if (unit.order <= TimeUnit.MINUTES.order) {
            calendar.set(Calendar.SECOND, 0)
        }
        if (unit.order <= TimeUnit.SECONDS.order) {
            calendar.set(Calendar.MILLISECOND, 0)
        }

        return Moment(calendar.time, timeZone, locale)
    }

    fun endOf(unit: TimeUnit): Moment {
        return startOf(unit).add(1, unit).subtract(1.milliseconds)
    }

    fun intervalSince(moment: Moment): Duration {
        val millisec: Long = (date.getTime() - moment.date.getTime())
        return Duration(millisec)
    }

    override fun equals(other: Any?): Boolean {
        if (other is Moment) {
            return date.equals(other.date)
        }

        return false
    }

    override fun compareTo(other: Moment): Int {
        return this.date.compareTo(other.date)
    }

    override fun toString(): String {
        return format()
    }

    private fun getCalendar(): Calendar {
        val cal = Calendar.getInstance(timeZone, locale)
        cal.setTime(date)
        return cal
    }

    private fun getDateFormat(dateFormat: String): SimpleDateFormat {
        val format = SimpleDateFormat(dateFormat, locale)
        format.timeZone = timeZone
        return format
    }

    private fun getCalendarNumber(type: Int): Int {
        return getCalendar().get(type)
    }

    private fun getFormatString(dateFormat: String): String {
        return getDateFormat(dateFormat).format(date)
    }
}
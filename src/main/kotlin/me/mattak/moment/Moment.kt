package me.mattak.moment

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

    private fun getCalendarNumber(type: Int): Int {
        val cal = Calendar.getInstance(timeZone, locale)
        cal.setTime(date)
        return cal.get(type)
    }

    override fun compareTo(other: Moment): Int {
        return this.date.compareTo(other.date)
    }

    fun intervalSince(moment: Moment): Duration {
        val millisec: Long = (date.getTime() - moment.date.getTime())
        return Duration(millisec)
    }
}
package me.mattak.moment

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Utc date shortcut.
 *
 * @return Moment of current time by UTC.
 */
fun utc(): Moment {
    val zone = TimeZone.getTimeZone("UTC")
    return Moment(timeZone = zone)
}

/**
 * Parse date by ambiguous formats.
 *
 * @param stringDate The string of date to parse.
 * @param timeZone The timeZone to parse.
 * @param locale The locale to parse.
 * @return return null if it cannot not parse.
 */
fun moment(
        stringDate: String,
        timeZone: TimeZone = TimeZone.getDefault(),
        locale: Locale = Locale.getDefault()
): Moment? {

    val formats: Array<SimpleDateFormat> = arrayOf(
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", locale),
            SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ", locale),
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", locale),
            SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSZ", locale),
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", locale),
            SimpleDateFormat("yyyy-MM-dd HH:mm:ss", locale),
            SimpleDateFormat("yyyy-MM-dd", locale),
            SimpleDateFormat("K:mm:ss a", locale), // 1:24:35 PM
            SimpleDateFormat("K:mm a", locale), // 1:24 AM
            SimpleDateFormat("MM/dd/yyyy", locale), // 02/29/2015
            SimpleDateFormat("MMMM d, yyyy h:mm a", locale), // June 1, 2015 1:23 PM
            SimpleDateFormat("MMMM d, yyyy", locale), // June 1, 2014
            // SimpleDateFormat("dddd, MMMM D, yyyy h:mm a", locale),
            SimpleDateFormat("yyyyyy-MM-dd", locale), // 002015-01-01
            SimpleDateFormat("yyyy-'W'ww-E", locale), // 2015-W14-Tue
            SimpleDateFormat("yyyy-'W'ww", locale), // 2015-W14
            SimpleDateFormat("yyyy-DDD", locale), // 2015-365
            SimpleDateFormat("HH:mm:ss.SSS", locale), // 12:08:12.9999
            SimpleDateFormat("HH:mm:ss", locale), // 12:08:12
            SimpleDateFormat("HH:mm", locale), // 12:09
            SimpleDateFormat("HH", locale) // 12
    )

    formats.forEach { format ->
        try {
            format.timeZone = timeZone
            val date: Date = format.parse(stringDate)
            return Moment(date, timeZone, locale)
        } catch (e: ParseException) {
        }
    }

    return null
}

/**
 * Parse moment from specified dateFormat.
 *
 * @param stringDate The string date to parse.
 * @param dateFormat The date format to parse.
 * @param timeZone The timeZone to parse.
 * @param locale The locale to parse.
 * @return return null if stringDate could not parse.
 */
fun moment(
        stringDate: String,
        dateFormat: String,
        timeZone: TimeZone = TimeZone.getDefault(),
        locale: Locale = Locale.getDefault()
): Moment? {
    val format = SimpleDateFormat(dateFormat, locale)
    format.timeZone = timeZone

    try {
        val date = format.parse(stringDate)
        return Moment(date, timeZone, locale)
    } catch (e: ParseException) {
        return null
    }
}

/**
 * Parse moment from array of params.
 *
 * @param params list of [year, month, day, hour, minute, seconds].
 * @param timeZone The timeZone to parse.
 * @param locale The locale to parse.
 * @return return null if Moment cannot parse.
 */
fun moment(
        params: IntArray,
        timeZone: TimeZone = TimeZone.getDefault(),
        locale: Locale = Locale.getDefault()
): Moment? {
    if (params.size() > 0) {
        val calendar = Calendar.getInstance(timeZone, locale)

        calendar.set(Calendar.YEAR, params.get(0))

        if (params.size() > 1) {
            calendar.set(Calendar.MONTH, params.get(1) - 1)
        }
        if (params.size() > 2) {
            calendar.set(Calendar.DAY_OF_MONTH, params.get(2))
        }
        if (params.size() > 3) {
            calendar.set(Calendar.HOUR_OF_DAY, params.get(3))
        }
        if (params.size() > 4) {
            calendar.set(Calendar.MINUTE, params.get(4))
        }
        if (params.size() > 5) {
            calendar.set(Calendar.SECOND, params.get(5))
        }

        return Moment(calendar.time, timeZone, locale)
    }

    return null
}

/**
 * Parse moment by dictionary.
 *
 * @param dictionary
 *   The map to parse.
 *   Example: `mapOf("year" to 2015, "month" to 9, "day" to 23, "hour" to 23, "minute" to 14, "second" to 24)`
 * @param timeZone
 *   The timeZone to parse.
 * @param locale
 *   The locale to parse.
 * @return return null if cannot create moment.
 */
fun moment(
        dictionary: Map<String, Int>,
        timeZone: TimeZone = TimeZone.getDefault(),
        locale: Locale = Locale.getDefault()
): Moment? {
    if (dictionary.size() > 0) {
        val calendar = Calendar.getInstance(timeZone, locale)

        dictionary.get("year")?.let {
            calendar.set(Calendar.YEAR, it)
        }
        dictionary.get("month")?.let {
            calendar.set(Calendar.MONTH, it - 1)
        }
        dictionary.get("day")?.let {
            calendar.set(Calendar.DAY_OF_MONTH, it)
        }
        dictionary.get("hour")?.let {
            calendar.set(Calendar.HOUR, it)
        }
        dictionary.get("minute")?.let {
            calendar.set(Calendar.MINUTE, it)
        }
        dictionary.get("second")?.let {
            calendar.set(Calendar.SECOND, it)
        }

        return Moment(calendar.time, timeZone, locale)
    }

    return null
}

/**
 * Create moment from milliseconds.
 *
 * @param milliseconds The milliseconds to parse. Example: `System.currentTimeMillis()`
 * @param timeZone The timeZone to parse.
 * @param locale The locale to parse.
 */
fun moment(
        milliseconds: Long,
        timeZone: TimeZone = TimeZone.getDefault(),
        locale: Locale = Locale.getDefault()
): Moment {
    val calendar = Calendar.getInstance(timeZone, locale)
    calendar.timeInMillis = milliseconds

    return Moment(calendar.time, timeZone, locale)
}
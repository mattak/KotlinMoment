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
            SimpleDateFormat("h:mm:ss a", locale),
            SimpleDateFormat("h:mm a", locale),
            SimpleDateFormat("MM/dd/yyyy", locale),
            SimpleDateFormat("MMMM d, yyyy", locale),
            // SimpleDateFormat("MMMM d, yyyy LT", locale),
            // SimpleDateFormat("dddd, MMMM D, yyyy LT", locale),
            SimpleDateFormat("yyyyyy-MM-dd", locale),
            SimpleDateFormat("yyyy-MM-dd", locale),
            SimpleDateFormat("GGGG-[W]WW-E", locale),
            SimpleDateFormat("GGGG-[W]WW", locale),
            SimpleDateFormat("yyyy-ddd", locale),
            SimpleDateFormat("HH:mm:ss.SSSS", locale),
            SimpleDateFormat("HH:mm:ss", locale),
            SimpleDateFormat("HH:mm", locale),
            SimpleDateFormat("HH", locale)
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

        if (params.size() > 0) {
            calendar.set(params[0], Calendar.YEAR)
        }
        if (params.size() > 1) {
            calendar.set(params[1] - 1, Calendar.MONTH)
        }
        if (params.size() > 2) {
            calendar.set(params[2], Calendar.DAY_OF_MONTH)
        }
        if (params.size() > 3) {
            calendar.set(params[3], Calendar.HOUR_OF_DAY)
        }
        if (params.size() > 4) {
            calendar.set(params[4], Calendar.MINUTE)
        }
        if (params.size() > 5) {
            calendar.set(params[5], Calendar.SECOND)
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
            calendar.set(it, Calendar.YEAR)
        }
        dictionary.get("month")?.let {
            calendar.set(it - 1, Calendar.MONTH)
        }
        dictionary.get("day")?.let {
            calendar.set(it, Calendar.DAY_OF_MONTH)
        }
        dictionary.get("hour")?.let {
            calendar.set(it, Calendar.HOUR)
        }
        dictionary.get("minute")?.let {
            calendar.set(it, Calendar.MINUTE)
        }
        dictionary.get("second")?.let {
            calendar.set(it, Calendar.SECOND)
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
): Moment? {
    val calendar = Calendar.getInstance(timeZone, locale)
    calendar.timeInMillis = milliseconds

    return Moment(calendar.time, timeZone, locale)
}
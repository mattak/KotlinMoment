package me.mattak.moment

/**
 * TimeUnit
 * Created by mattak on 15/09/23.
 */
public enum class TimeUnit(
        val order: Int,
        val durationMultiply: Long
) {
    YEARS(1, 31536000000),
    QUARTERS(2, 7776000000),
    MONTHS(3, 2592000000),
    DAYS(4, 86400000),
    HOURS(5, 3600000),
    MINUTES(6, 60000),
    SECONDS(7, 1000),
    MILLISECONDS(8, 1)
    ;
}

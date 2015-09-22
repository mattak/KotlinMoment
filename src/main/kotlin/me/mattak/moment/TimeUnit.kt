package me.mattak.moment

/**
 * TimeUnit
 * Created by mattak on 15/09/23.
 */
public enum class TimeUnit(val order: Int) {
    YEARS(1),
    QUARTERS(2),
    MONTHS(3),
    DAYS(4),
    HOURS(5),
    MINUTES(6),
    SECONDS(7),
    MILLISECONDS(8)
    ;
}
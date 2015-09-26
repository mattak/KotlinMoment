package me.mattak.moment

/**
 * Duration class is interval of milliseconds. The precision is very rough. (especially month and years)
 * Created by mattak on 15/09/02.
 */
public class Duration(val interval: Long) : Comparable<Duration> {
    val years: Double
        get() = millisec.toDouble() / TimeUnit.YEARS.durationMultiply

    val quarters: Double
        get() = millisec.toDouble() / TimeUnit.QUARTERS.durationMultiply

    val months: Double
        get() = millisec.toDouble() / TimeUnit.MONTHS.durationMultiply

    val days: Double
        get() = millisec.toDouble() / TimeUnit.DAYS.durationMultiply

    val hours: Double
        get() = millisec.toDouble() / TimeUnit.HOURS.durationMultiply

    val minutes: Double
        get() = millisec.toDouble() / TimeUnit.MINUTES.durationMultiply

    val seconds: Double
        get() = millisec.toDouble() / TimeUnit.SECONDS.durationMultiply

    val millisec: Double
        get() = interval.toDouble()

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

    override fun toString(): String {
        val buffer = StringBuffer()

        if (years > 0) {
            return buffer.append(years.toLong())
                    .append(" years")
                    .toString()
        } else if (months > 0) {
            return buffer.append(months.toLong())
                    .append(" months")
                    .toString()
        } else if (days > 0) {
            return buffer.append(days.toLong())
                    .append(" days")
                    .toString()
        } else if (minutes > 0) {
            return buffer.append(minutes.toLong())
                    .append(" minutes")
                    .toString()
        } else if (seconds > 0) {
            return buffer.append(seconds.toLong())
                    .append(" seconds")
                    .toString()
        } else {
            return buffer.append(millisec.toLong())
                    .append(" milliseconds")
                    .toString()
        }
    }

    override fun compareTo(other: Duration): Int {
        if (this.millisec > other.millisec) {
            return 1
        } else if (this.millisec < other.millisec) {
            return -1
        } else {
            return 0
        }
    }
}
package me.mattak.moment

import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Duration class is interval of milliseconds. The precision is very rough. (especially month and years)
 * Created by mattak on 15/09/02.
 */
public class Duration(val millisec: Long) : Comparable<Duration> {
    val years: Double
        get() = millisec.toDouble() / 31536000000

    val quarters: Double
        get() = millisec.toDouble() / 7776000000

    val months: Double
        get() = millisec.toDouble() / 2592000000

    val days: Double
        get() = millisec.toDouble() / 86400000

    val hours: Double
        get() = millisec.toDouble() / 3600000

    val minutes: Double
        get() = millisec.toDouble() / 60000

    val seconds: Double
        get() = millisec.toDouble() / 1000

    fun ago() : Moment {
        throw IllegalStateException("not implemented")
    }

    fun add(duration: Duration) : Duration {
        return Duration(this.millisec + duration.millisec)
    }

    fun subtract(duration: Duration): Duration {
        return Duration(this.millisec - duration.millisec)
    }

    override fun toString(): String {
        // TODO: write correct logic
        val m: Int = minutes.toInt()
        val s: Int = seconds.toInt() % 60
        return java.lang.String.format("%d:%02d", m, s)
    }

    override fun compareTo(other: Duration): Int {
        if (this.millisec > other.millisec) {
            return 1
        }
        else if (this.millisec < other.millisec){
            return -1
        }
        else {
            return 0
        }
    }
}
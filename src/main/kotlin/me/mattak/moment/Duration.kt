package me.mattak.moment

import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Duration class
 * Created by mattak on 15/09/02.
 */
public class Duration(val millisec: Long) : Comparable<Duration> {
    fun years(): Double {
        return millisec.toDouble() / 31536000000
    }

    fun quarters(): Double {
        return millisec.toDouble() / 7776000000
    }

    fun months(): Double {
        return millisec.toDouble() / 2592000000
    }

    fun days(): Double {
        return millisec.toDouble() / 86400000
    }

    fun hours(): Double {
        return millisec.toDouble() / 3600000
    }

    fun minutes(): Double {
        return millisec.toDouble() / 60000
    }

    fun seconds(): Double {
        return millisec.toDouble() / 1000
    }

    // fun ago() : Moment {
    // }

    fun add(duration: Duration) : Duration {
        return Duration(this.millisec + duration.millisec)
    }

    fun subtract(duration: Duration): Duration {
        return Duration(this.millisec - duration.millisec)
    }

    override fun toString(): String {
        // TODO: write correct logic
        val m: Int = minutes().toInt()
        val s: Int = seconds().toInt() % 60
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
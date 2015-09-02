package me.mattak.moment

/**
 * Extensions.kt
 * Created by mattak on 15/09/02.
 */

val Int.milliseconds: Duration
    get() = Duration(this.toLong())

val Int.seconds: Duration
    get() = Duration(this.toLong() * 1000)

val Int.minutes: Duration
    get() = Duration(this.toLong() * 60000)

val Int.hours: Duration
    get() = Duration(this.toLong() * 3600000)

val Int.days: Duration
    get() = Duration(this.toLong() * 86400000)

val Int.months: Duration
    get() = Duration(this.toLong() * 2592000000)

val Int.quarters: Duration
    get() = Duration(this.toLong() * 7776000000)

val Int.years: Duration
    get() = Duration(this.toLong() * 31536000000)
package me.mattak.moment

val Int.milliseconds: Duration
    get() = Duration(this.toLong())

val Int.seconds: Duration
    get() = Duration(this.toLong() * TimeUnit.SECONDS.durationMultiply)

val Int.minutes: Duration
    get() = Duration(this.toLong() * TimeUnit.MINUTES.durationMultiply)

val Int.hours: Duration
    get() = Duration(this.toLong() * TimeUnit.HOURS.durationMultiply)

val Int.days: Duration
    get() = Duration(this.toLong() * TimeUnit.DAYS.durationMultiply)

val Int.months: Duration
    get() = Duration(this.toLong() * TimeUnit.MONTHS.durationMultiply)

val Int.quarters: Duration
    get() = Duration(this.toLong() * TimeUnit.QUARTERS.durationMultiply)

val Int.years: Duration
    get() = Duration(this.toLong() * TimeUnit.YEARS.durationMultiply)
package me.mattak.moment

fun Moment.minus(rhs: Moment): Duration {
    return this.intervalSince(rhs)
}
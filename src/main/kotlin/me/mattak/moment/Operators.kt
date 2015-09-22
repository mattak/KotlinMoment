package me.mattak.moment

fun Moment.minus(rhs: Moment): Duration {
    return this.intervalSince(rhs)
}

fun Moment.plus(rhs: Duration): Moment {
    return this.add(rhs)
}

fun Duration.plus(rhs: Moment): Moment {
    return rhs.add(this)
}

fun Moment.minus(rhs: Duration): Moment {
    return this.subtract(rhs)
}

fun Duration.minus(rhs: Moment): Moment {
    return rhs.subtract(this)
}

fun Duration.plus(rhs: Duration): Duration {
    return rhs.add(this)
}

fun Duration.minus(rhs: Duration): Duration {
    return rhs.subtract(this)
}
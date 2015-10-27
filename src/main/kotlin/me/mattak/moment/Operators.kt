package me.mattak.moment

operator fun Moment.minus(rhs: Moment): Duration {
    return this.intervalSince(rhs)
}

operator fun Moment.plus(rhs: Duration): Moment {
    return this.add(rhs)
}

operator fun Moment.minus(rhs: Duration): Moment {
    return this.subtract(rhs)
}

operator fun Duration.plus(rhs: Moment): Moment {
    return rhs.add(this)
}

operator fun Duration.minus(rhs: Moment): Moment {
    return rhs.subtract(this)
}

operator fun Duration.plus(rhs: Duration): Duration {
    return this.add(rhs)
}

operator fun Duration.minus(rhs: Duration): Duration {
    return this.subtract(rhs)
}
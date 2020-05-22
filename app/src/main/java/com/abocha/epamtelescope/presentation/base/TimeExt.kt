package com.abocha.epamtelescope.presentation.base

/**
 * @author Magomedov Abakar
 */

fun Int.secondsToMinutesRepresentation(): String {
    val localPair = getMinutesCounter(this)
    val minutes = localPair.first.toString()
    var seconds = localPair.second.toString()
    if (localPair.second < 10) {
        seconds = seconds.padStart(2, '0')
    }
    return "$minutes:$seconds"
}

private fun getMinutesCounter(seconds: Int): Pair<Int, Int> {
    var minutesCounter = 0
    var localSeconds = seconds
    while (localSeconds >= 60) {
        minutesCounter++
        localSeconds -= 60
    }
    return Pair(minutesCounter, localSeconds)
}
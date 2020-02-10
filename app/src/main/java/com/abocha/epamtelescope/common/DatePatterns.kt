package com.abocha.epamtelescope.common

import org.threeten.bp.LocalDate
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter

/**
 * Created by Oleg Sheliakin on 2020-01-27.
 * Contact me by email - olegsheliakin@gmail.com
 */

object DatePatterns {
    const val DATE_FORMAT = "dd.MM.yyyy"
    const val TIME_FORMAT = "HH:mm"
}

fun LocalDate.formatted() = this.format(DateTimeFormatter.ofPattern(DatePatterns.DATE_FORMAT))
fun ZonedDateTime.formatted() = this.format(DateTimeFormatter.ofPattern(DatePatterns.DATE_FORMAT))

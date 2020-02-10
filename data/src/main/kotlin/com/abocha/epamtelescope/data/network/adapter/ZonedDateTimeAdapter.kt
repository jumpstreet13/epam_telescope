package com.abocha.epamtelescope.data.network.adapter

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter

/**
 * Created by Oleg Sheliakin on 2020-02-03.
 * Contact me by email - olegsheliakin@gmail.com
 */
object ZonedDateTimeAdapter {

    private val formatter = DateTimeFormatter.ISO_DATE_TIME

    @FromJson
    fun toZonedDateTime(value: String): ZonedDateTime =
        LocalDateTime.parse(value, formatter).atZone(ZoneId.of("UTC"))
            .withZoneSameInstant(ZoneId.systemDefault())

    @ToJson
    fun fromZonedDateTime(value: ZonedDateTime): String =
        value.withZoneSameInstant(ZoneId.of("UTC")).format(formatter)

}

object LocalDateAdapter {

    private val formatter = DateTimeFormatter.ISO_DATE

    @FromJson
    fun toZonedDateTime(value: String): LocalDate =
        LocalDate.parse(value, formatter)

    @ToJson
    fun fromZonedDateTime(value: LocalDate): String =
        value.format(formatter)

}

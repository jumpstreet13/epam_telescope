package com.abocha.epamtelescope.entities

import org.threeten.bp.ZonedDateTime

data class PlatfromPhotos(
    val id: Long,
    val date: ZonedDateTime,
    val inspectorId: Long,
    val platformId: Long,
    val campaignId: Long
)

package com.abocha.epamtelescope.entities

import org.threeten.bp.ZonedDateTime

data class CathodicProtection(
    val id: Long,
    val date: ZonedDateTime,
    val inspectorId: Long,
    val platformId: Long,
    val campaignId: Long,
    val calibrationNumber: Int?,
    val depthTenReading: Int?,
    val depthTwentyReading: Int?,
    val depthThirtyReading: Int?,
    val depthFortyReading: Int?,
    val legId: String,
    val comment: String,
    val photos: List<Photo>
)

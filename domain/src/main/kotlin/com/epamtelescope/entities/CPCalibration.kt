package com.abocha.epamtelescope.entities

import org.threeten.bp.ZonedDateTime

data class CPCalibration(
    val id: Long,
    val date: ZonedDateTime,
    val inspectorId: Long,
    val platformId: Long,
    val campaignId: Long,
    val calibrationNumber: Int?,
    val multimeterBrand: String,
    val multimeterSN: String,
    val preCalibration: Int?,
    val postCalibration: Int?
)

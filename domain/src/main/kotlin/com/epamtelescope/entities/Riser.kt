package com.abocha.epamtelescope.entities

import org.threeten.bp.ZonedDateTime

data class Riser(
    val id: Long,
    val date: ZonedDateTime,
    val inspectorId: Long,
    val platformId: Long,
    val campaignId: Long,
    val platfromArea: Dictionary? = null,
    val riserType: Dictionary? = null,
    val segmentNumber: Int? = null,
    val apirp14CName: String = "",
    val drawingId: Int? = null,
    val diamiter: Int? = null,
    val mechDamage: Dictionary? = null,
    val mechDamageComment: String = "",
    val coatingCondition: Dictionary? = null,
    val coatingConditionComment: String = "",
    val readingAboveFlange: String = "",
    val readingBellowFlange: String = "",
    val repairsComment: String = "",
    val hseComment: String = "",
    val isoFlangeAccess: String = "",
    val paintedOnTheRiser: String = "",
    val comments: String = "",
    val riserGuardPresent: Boolean = false,
    val flangeIsoKitInstalled: Boolean = false,
    val flangeIsoKitWorking: Boolean = false,
    val hseHazard: Boolean = false
)

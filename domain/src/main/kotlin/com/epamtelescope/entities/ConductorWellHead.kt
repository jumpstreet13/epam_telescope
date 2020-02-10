package com.abocha.epamtelescope.entities

import org.threeten.bp.ZonedDateTime

data class ConductorWellHead(
    val id: Long,
    val date: ZonedDateTime,
    val inspectorId: Long,
    val platformId: Long,
    val campaignId: Long,
    val mechanicalDamage: Dictionary?,
    val mechanicalDamageComment: String,
    val coatingCondition: Dictionary?,
    val coatingConditionComment: String,
    val conductorArea: Dictionary?,
    val hseComment: String,
    val comment: String,
    val hseHazard: Boolean,
    val wellSlot: WellSlot?,
    val diameter: Int?,
    val gap: Int?,
    val shims: Boolean,
    val guideDamage: Boolean
)

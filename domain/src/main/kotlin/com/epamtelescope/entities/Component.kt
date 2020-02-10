package com.abocha.epamtelescope.entities

import org.threeten.bp.ZonedDateTime

data class Component(
    val id: Long,
    val date: ZonedDateTime,
    val inspectorId: Long,
    val platformId: Long,
    val campaignId: Long,
    val componentType: Dictionary?,
    val mechanicalDamage: Dictionary?,
    val platformArea: Dictionary?,
    val mechanicalDamageComment: String,
    val coatingCondition: Dictionary?,
    val coatingConditionComment: String,
    val hseComment: String,
    val comment: String,
    val hseHazard: Boolean
)

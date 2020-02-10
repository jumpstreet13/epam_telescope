package com.abocha.epamtelescope.entities

import org.threeten.bp.ZonedDateTime

/**
 * Created by Oleg Sheliakin on 2020-01-24.
 * Contact me by email - olegsheliakin@gmail.com
 */
data class RiserClamp(
    val id: Long,
    val date: ZonedDateTime,
    val drawingReference: Int?,
    val clamps: List<ClampData>,
    val inspectorId: Long,
    val platformId: Long,
    val campaignId: Long
)

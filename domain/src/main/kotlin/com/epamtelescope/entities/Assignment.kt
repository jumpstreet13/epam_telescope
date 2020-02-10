package com.abocha.epamtelescope.entities

import org.threeten.bp.ZonedDateTime

/**
 * Created by Oleg Sheliakin on 2020-01-14.
 * Contact me by email - olegsheliakin@gmail.com
 */
data class Assignment(
    val id: Long,
    val createdAt: ZonedDateTime,
    val modifiedAt: ZonedDateTime,
    val inspectionCampaign: InspectionCampaign
)

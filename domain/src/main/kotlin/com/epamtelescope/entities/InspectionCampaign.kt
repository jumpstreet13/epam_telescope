package com.abocha.epamtelescope.entities

import org.threeten.bp.ZonedDateTime

/**
 * Created by Oleg Sheliakin on 2020-02-03.
 * Contact me by email - olegsheliakin@gmail.com
 */
data class InspectionCampaign(
    val inspectionId: Long,
    val title: String,
    val startDate: ZonedDateTime,
    val endDate: ZonedDateTime
)

package com.abocha.epamtelescope.usecases.changepassword

import org.threeten.bp.ZonedDateTime

/**
 * Created by Oleg Sheliakin on 2020-01-29.
 * Contact me by email - olegsheliakin@gmail.com
 */
data class SurveyParams(
    val id: Long,
    val date: ZonedDateTime,
    val platformId: Long,
    val campaignId: Long,
    val isDraft: Boolean
)

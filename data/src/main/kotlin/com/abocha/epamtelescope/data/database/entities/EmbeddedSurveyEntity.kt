package com.abocha.epamtelescope.data.database.entities

import com.abocha.epamtelescope.entities.SyncState
import org.threeten.bp.ZonedDateTime

/**
 * Created by Oleg Sheliakin on 2020-01-28.
 * Contact me by email - olegsheliakin@gmail.com
 */
data class EmbeddedSurveyEntity(
    val syncState: SyncState,
    val date: ZonedDateTime,
    val inspectorId: Long,
    val platformId: Long,
    val campaignId: Long
)

interface SurveyEntity {
    val id: Long
    val embedded: EmbeddedSurveyEntity
}

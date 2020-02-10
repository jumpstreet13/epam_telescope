package com.abocha.epamtelescope.data.database.entities

import com.abocha.epamtelescope.entities.SurveyTask
import com.abocha.epamtelescope.entities.SurveyType
import com.abocha.epamtelescope.entities.SyncState
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter

private const val DATE_FORMAT = "dd.MM.yyyy"

data class SurveyShortEntity(
    val id: Long,
    val syncState: SyncState,
    val date: ZonedDateTime,
    val inspectorId: Long,
    val platformId: Long,
    val campaignId: Long
)

fun SurveyShortEntity.toDomainModel(
    surveyType: SurveyType
) = SurveyTask(
    id = id,
    title = date.format(DateTimeFormatter.ofPattern(DATE_FORMAT)),
    surveyType = surveyType,
    syncState = syncState
)

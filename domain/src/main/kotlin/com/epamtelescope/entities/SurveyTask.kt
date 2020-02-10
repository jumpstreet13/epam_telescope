package com.abocha.epamtelescope.entities

data class SurveyTask(
    val id: Long,
    val title: String,
    val surveyType: SurveyType,
    val syncState: SyncState
)

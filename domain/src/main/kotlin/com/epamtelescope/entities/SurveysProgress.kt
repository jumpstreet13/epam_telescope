package com.abocha.epamtelescope.entities

data class SurveysProgress(
    val surveyType: SurveyType,
    val syncedNumber: Int = 0,
    val totalNumber: Int = 0
)

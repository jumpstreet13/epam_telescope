package com.abocha.epamtelescope.entities

data class Survey(
    val title: String,
    val surveyType: SurveyType,
    val syncSurveysNumber: Int = 0,
    val totalSurveyNumber: Int = 0
)

enum class SurveyType {
    OVERALL_PLATFORM_PHOTOS,
    COMPONENT,
    CONDUCTOR_AND_WELLHEAD,
    RISER,
    RISER_CLAMPS,
    CATHODIC_PROTECTION,
    ISIMS,
    CATHODIC_PROTECTION_CALIBRATION;
}

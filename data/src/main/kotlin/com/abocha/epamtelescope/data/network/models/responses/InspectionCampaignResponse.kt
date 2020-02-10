package com.abocha.epamtelescope.data.network.models.responses

import com.abocha.epamtelescope.entities.InspectionCampaign
import com.squareup.moshi.Json
import org.threeten.bp.LocalDate
import org.threeten.bp.ZoneId

data class InspectionCampaignResponse(
    val id: Long,
    val title: String,
    @field:Json(name = "start_date")
    val startDate: LocalDate,
    @field:Json(name = "end_date")
    val endDate: LocalDate
)

fun InspectionCampaignResponse.toDomainModel() =
    InspectionCampaign(
        inspectionId = id,
        title = title,
        startDate = startDate.atStartOfDay(ZoneId.systemDefault()),
        endDate = endDate.atStartOfDay(ZoneId.systemDefault())
    )

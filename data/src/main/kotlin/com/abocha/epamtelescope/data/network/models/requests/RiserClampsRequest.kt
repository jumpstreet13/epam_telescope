package com.abocha.epamtelescope.data.network.models.requests

import com.abocha.epamtelescope.entities.ClampData
import com.abocha.epamtelescope.entities.RiserClamp
import com.squareup.moshi.Json
import org.threeten.bp.format.DateTimeFormatter

/**
 * Created by Oleg Sheliakin on 2020-01-13.
 * Contact me by email - olegsheliakin@gmail.com
 */
data class RiserClampsRequest(
    val platform: Long,
    val campaign: Long,
    val inspector: Long,
    @field:Json(name = "date_created")
    val createdDate: String,
    @field:Json(name = "drawing_reference")
    val drawingReference: Int?,
    @field:Json(name = "clamp_data")
    val clamps: List<ClampDataRequest>
)

data class ClampDataRequest(
    val number: Int,
    @field:Json(name = "riser_clamp_type")
    val riserClampType: String?,
    @field:Json(name = "platform_clamp_type")
    val platformClampType: String?,
    @field:Json(name = "clamp_elevation")
    val clampElevation: Int?,
    @field:Json(name = "fasteners_info")
    val fastenersInfo: String?,
    @field:Json(name = "does_clamp_have_gasket")
    val hasGasket: Boolean
)

fun ClampData.toRequest() =
    ClampDataRequest(
        number = number,
        riserClampType = riserType?.title,
        platformClampType = clampType?.title,
        clampElevation = clampElevation,
        fastenersInfo = issue,
        hasGasket = hasGasket
    )

fun RiserClamp.toRequest() =
    RiserClampsRequest(
        platform = platformId,
        campaign = campaignId,
        inspector = inspectorId,
        createdDate = date.format(DateTimeFormatter.ISO_ZONED_DATE_TIME),
        drawingReference = drawingReference,
        clamps = clamps.map { it.toRequest() }
    )

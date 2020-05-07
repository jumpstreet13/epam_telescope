package com.abocha.epamtelescope.data.network.models.requests

import com.squareup.moshi.Json

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


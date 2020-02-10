package com.abocha.epamtelescope.data.network.models.responses

import com.abocha.epamtelescope.entities.WellSlot
import com.squareup.moshi.Json

/**
 * Created by Oleg Sheliakin on 2020-02-04.
 * Contact me by email - olegsheliakin@gmail.com
 */
data class PlatformResponse(
    val id: Long,
    val title: String,
    @field:Json(name = "well_slots")
    val wellSlots: List<WellSlot>
)

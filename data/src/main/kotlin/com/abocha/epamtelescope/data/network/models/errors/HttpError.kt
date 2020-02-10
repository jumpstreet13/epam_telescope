package com.abocha.epamtelescope.data.network.models.errors

import com.squareup.moshi.Json

data class HttpError(@field:Json(name = "detail") val detailMessage : String)

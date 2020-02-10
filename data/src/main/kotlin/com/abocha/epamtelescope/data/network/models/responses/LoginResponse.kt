package com.abocha.epamtelescope.data.network.models.responses

import com.squareup.moshi.Json

data class LoginResponse(
    @field:Json(name = "access") val accessToken: String,
    @field:Json(name = "refresh") val refreshToken: String,
    @field:Json(name = "user") val userProfileResponse: UserProfileResponse
)

package com.abocha.epamtelescope.data.network.models.responses

import com.squareup.moshi.Json

data class ChangePasswordTokenResponse(
    @field:Json(name = "change_password_token") val changePasswordToken: String
)

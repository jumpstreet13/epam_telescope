package com.abocha.epamtelescope.data.network.models.requests

import com.squareup.moshi.Json

data class FinishRecoveryPasswordRequest(
    @field:Json(name = "recovery_token") val recoveryToken: String,
    @field:Json(name = "new_password_1") val newPassword: String,
    @field:Json(name = "new_password_2") val confirmationPassword: String
)

package com.abocha.epamtelescope.data.network.models.requests

import com.squareup.moshi.Json

data class FinishChangePasswordRequest(
    @field:Json(name = "change_password_token") val changePasswordToken: String,
    @field:Json(name = "old_password") val oldPassword: String,
    @field:Json(name = "new_password_1") val newPassword: String,
    @field:Json(name = "new_password_2") val confirmationPassword: String
)

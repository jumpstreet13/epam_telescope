package com.abocha.epamtelescope.data.network.models.responses

import com.squareup.moshi.Json

data class RecoveryTokenResponse(@field:Json(name = "recovery_token") val recoveryToken: String)

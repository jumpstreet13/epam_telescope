package com.abocha.epamtelescope.data.network.models.requests

data class ConfirmEmailRequest(val email: String, val code: String)

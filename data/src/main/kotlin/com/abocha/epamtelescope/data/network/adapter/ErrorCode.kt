package com.abocha.epamtelescope.data.network.adapter

enum class ErrorCode(val codeName: String?) {
    ERROR("error"),
    VALIDATION("invalid"),
    PERMISSION_DENIED("permission_denied"),
    UNKNOWN(null);

    companion object {
        fun resolve(codeName: String): ErrorCode =
            values().firstOrNull { it.codeName == codeName } ?: UNKNOWN
    }
}

package com.abocha.epamtelescope.data.network.models.errors

import com.abocha.epamtelescope.data.network.adapter.ErrorCode
import com.abocha.epamtelescope.rxerroradapter.HttpResponseError
import com.abocha.epamtelescope.rxerroradapter.IntKey
import com.abocha.epamtelescope.rxerroradapter.Key
import com.abocha.epamtelescope.rxerroradapter.KeySelector
import org.json.JSONObject

/**
 * Created by Oleg Sheliakin on 2020-01-16.
 * Contact me by email - olegsheliakin@gmail.com
 */
private const val BAD_REQUEST = 400
private const val FORBIDDEN = 403

class HttpKeySelector : KeySelector {

    override fun invoke(error: HttpResponseError): Key =
        if (error.httpCode == BAD_REQUEST || error.httpCode == FORBIDDEN) {
            val jsonObject = JSONObject(error.errorBodyString ?: "")
            val errorCode = ErrorCode.resolve(jsonObject.optString(ERROR_CODE_KEY))
            ErrorCodeKey(errorCode)
        } else {
            IntKey(error.httpCode)
        }

    companion object {
        const val ERROR_CODE_KEY = "error_code"
    }
}

class ErrorCodeKey(private val errorCode: ErrorCode) : Key {
    override fun generateKey(): String =
        errorCode.name

}

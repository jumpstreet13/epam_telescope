package com.abocha.epamtelescope.data.network.adapter

import com.abocha.epamtelescope.exceptions.HttpException
import com.abocha.epamtelescope.exceptions.UnknownErrorException
import com.abocha.epamtelescope.rxerroradapter.ErrorAdapter
import com.abocha.epamtelescope.rxerroradapter.HttpResponseError
import org.json.JSONObject

/**
 * Created by Oleg Sheliakin on 2020-01-13.
 * Contact me by email - olegsheliakin@gmail.com
 */

object HttpErrorAdapter : ErrorAdapter<HttpResponseError> {

    private const val DETAIL_KEY = "detail"

    override fun invoke(response: HttpResponseError): Throwable? =
        mapError(response)

    private fun mapError(response: HttpResponseError) =
        response.errorBodyString?.let { errorBody ->
            val jsonObject = JSONObject(errorBody)
            HttpException(response.httpCode, jsonObject.optString(DETAIL_KEY))
        } ?: UnknownErrorException

}

package com.abocha.epamtelescope.data.network.services

import com.abocha.epamtelescope.data.network.models.responses.LoginResponse
import com.abocha.epamtelescope.oauth2token.tokenParser.OAuth2TokenParser
import com.squareup.moshi.Moshi
import okhttp3.ResponseBody
import javax.inject.Inject

class OAuthTokenParserImpl @Inject constructor(private val moshi: Moshi) : OAuth2TokenParser {

    override fun parseTokens(body: ResponseBody?): Pair<String?, String?>? =
        body?.let {
            val loginResponse = moshi.adapter(LoginResponse::class.java).fromJson(it.string())

            loginResponse?.accessToken to loginResponse?.refreshToken
        }
}

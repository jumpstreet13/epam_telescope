package com.abocha.epamtelescope.oauth2token.interceptor

import com.abocha.epamtelescope.oauth2token.CODE_OK
import com.abocha.epamtelescope.oauth2token.UNAUTHORIZED_ERROR_CODE
import com.abocha.epamtelescope.oauth2token.exception.RefreshTokenExpiredException
import com.abocha.epamtelescope.oauth2token.header.OAuth2HeaderProvider
import com.abocha.epamtelescope.oauth2token.header.OAuth2HeaderProviderImpl
import com.abocha.epamtelescope.oauth2token.session.OAuth2Session
import com.abocha.epamtelescope.oauth2token.tokenParser.OAuth2TokenParser
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.Call
import java.io.IOException

/**
 * Created by Oleg Sheliakin on 2019-12-23.
 * Contact me by email - olegsheliakin@gmail.com
 */
class OAuth2Interceptor(private val config: ConfigOAuth2Interceptor) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = requestWithToken(chain)
        if (response.code() != config.unAuthorizedCode) {
            return response
        }
        val previousToken = config.session.accessToken
        synchronized(this) {
            return when {
                previousToken != config.session.accessToken -> requestWithToken(chain)
                else -> requestNewAccessToken()?.run {
                    when (code()) {
                        CODE_OK -> {
                            val tokens = config.tokenParser.parseTokens(body())
                            config.session.accessToken = tokens?.first
                            config.session.refreshToken = tokens?.second
                            requestWithToken(chain)
                        }
                        else -> throw RefreshTokenExpiredException()
                    }
                } ?: response
            }
        }
    }

    @Throws(IOException::class)
    private fun requestNewAccessToken(): retrofit2.Response<ResponseBody>? {
        config.session.refreshToken?.let {
            return config.newAccessTokenCall.invoke(it).execute()
        }
        return null
    }

    @Throws(IOException::class)
    private fun requestWithToken(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .apply {

                config.session.accessToken?.let {
                    addHeader(
                        config.headerProvider.provideHeaderName(),
                        config.headerProvider.provideHeaderValue(it)
                    )
                }
            }
            .build()
        return chain.proceed(request)
    }
}

data class ConfigOAuth2Interceptor(
    val session: OAuth2Session,
    val newAccessTokenCall: (String) -> Call<ResponseBody>,
    val tokenParser: OAuth2TokenParser,
    val unAuthorizedCode: Int = UNAUTHORIZED_ERROR_CODE,
    val headerProvider: OAuth2HeaderProvider = OAuth2HeaderProviderImpl()
)

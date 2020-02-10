package com.abocha.epamtelescope.oauth2token.header

import com.abocha.epamtelescope.oauth2token.ACCESS_TOKEN_TYPE
import com.abocha.epamtelescope.oauth2token.AUTHORIZATION_HEADER

/**
 * Created by Oleg Sheliakin on 2019-12-23.
 * Contact me by email - olegsheliakin@gmail.com
 */
interface OAuth2HeaderProvider {

    fun provideHeaderName(): String

    fun provideHeaderValue(accessToken: String): String
}

class OAuth2HeaderProviderImpl : OAuth2HeaderProvider {

    override fun provideHeaderName() = AUTHORIZATION_HEADER

    override fun provideHeaderValue(accessToken: String) = "$ACCESS_TOKEN_TYPE $accessToken"
}

package com.abocha.epamtelescope.data.network.di

import android.content.Context
import android.content.SharedPreferences
import com.abocha.epamtelescope.data.network.models.requests.RefreshRequest
import com.abocha.epamtelescope.data.network.services.AuthService
import com.abocha.epamtelescope.data.network.services.OAuthTokenParserImpl
import com.abocha.epamtelescope.oauth2token.interceptor.ConfigOAuth2Interceptor
import com.abocha.epamtelescope.oauth2token.interceptor.OAuth2Interceptor
import com.abocha.epamtelescope.oauth2token.session.OAuth2Session
import com.abocha.epamtelescope.oauth2token.session.OAuth2SessionImpl
import com.abocha.epamtelescope.oauth2token.tokenParser.OAuth2TokenParser
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Oleg Sheliakin on 2019-12-23.
 * Contact me by email - olegsheliakin@gmail.com
 */
@Module
abstract class OAuth2TokenModule {

    @Module
    companion object {

        private const val OAUTH_SESSION_PREF_NAME = "oauth_session_pref_name"

        @JvmStatic
        @Provides
        @Singleton
        fun provideOAuthConfig(
            oAuth2Session: OAuth2Session,
            tokenParser: OAuth2TokenParser,
            api: AuthService
        ): ConfigOAuth2Interceptor =
            ConfigOAuth2Interceptor(
                session = oAuth2Session,
                newAccessTokenCall = { api.refresh(RefreshRequest(it)) },
                tokenParser = tokenParser
            )

        @JvmStatic
        @Provides
        @Singleton
        fun provideOAuthInterceptor(configOAuth2Interceptor: ConfigOAuth2Interceptor): OAuth2Interceptor =
            OAuth2Interceptor(configOAuth2Interceptor)

        @JvmStatic
        @Singleton
        @Provides
        fun provideOAuth2SessionSharedPreferences(applicationContext: Context): SharedPreferences =
            applicationContext.getSharedPreferences(OAUTH_SESSION_PREF_NAME, Context.MODE_PRIVATE)

        @JvmStatic
        @Singleton
        @Provides
        fun provideOAuth2Session(sharedPreferences: SharedPreferences): OAuth2Session =
            OAuth2SessionImpl(sharedPreferences)
    }

    @Binds
    abstract fun provideOAuth2TokenParser(impl: OAuthTokenParserImpl): OAuth2TokenParser
}

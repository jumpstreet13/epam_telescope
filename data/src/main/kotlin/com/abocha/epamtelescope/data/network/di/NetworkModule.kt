package com.abocha.epamtelescope.data.network.di

import com.abocha.epamtelescope.data.BuildConfig
import com.abocha.epamtelescope.data.network.adapter.ErrorCode
import com.abocha.epamtelescope.data.network.adapter.HttpErrorAdapter
import com.abocha.epamtelescope.data.network.adapter.HttpValidationErrorAdapter
import com.abocha.epamtelescope.data.network.adapter.MoshiProvider
import com.abocha.epamtelescope.data.network.models.errors.ErrorCodeKey
import com.abocha.epamtelescope.data.network.models.errors.HttpKeySelector
import com.abocha.epamtelescope.exceptions.HttpException
import com.abocha.epamtelescope.exceptions.ServiceUnavailableException
import com.abocha.epamtelescope.exceptions.UserSessionExpiredException
import com.abocha.epamtelescope.oauth2token.exception.RefreshTokenExpiredException
import com.abocha.epamtelescope.oauth2token.interceptor.OAuth2Interceptor
import com.abocha.epamtelescope.rxerroradapter.ErrorAdapterProvider
import com.abocha.epamtelescope.rxerroradapter.HttpExceptionAdapter
import com.abocha.epamtelescope.rxerroradapter.RxErrorCallAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val NETWORK_TIMEOUT = 30L
private const val INTERNAL_SERVER_ERROR = 500
private const val RELEASE_TYPE = "release"

@Module
object NetworkModule {

    @Provides
    fun httpAdapter(): HttpExceptionAdapter =
        HttpExceptionAdapter.create(keySelector = HttpKeySelector()) {
            setDefaultAdapter { HttpException(it.httpCode, it.message) }
            register(INTERNAL_SERVER_ERROR) { ServiceUnavailableException }
            register(ErrorCodeKey(ErrorCode.ERROR), HttpErrorAdapter)
            register(ErrorCodeKey(ErrorCode.PERMISSION_DENIED), HttpErrorAdapter)
            register(ErrorCodeKey(ErrorCode.VALIDATION), HttpValidationErrorAdapter())
        }


    @Provides
    fun provideErrorAdapterProvider(httpAdapter: HttpExceptionAdapter) =
        ErrorAdapterProvider.create {
            register(RefreshTokenExpiredException::class) { UserSessionExpiredException }
            registerHttpExceptionAdapter(httpAdapter)
        }

    @Provides
    fun errorAdapterFactory(errorAdapterProvider: ErrorAdapterProvider): RxErrorCallAdapterFactory =
        RxErrorCallAdapterFactory.create(
            RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()), errorAdapterProvider
        )

    @Provides
    @Default
    fun provideOkHttp(): OkHttpClient =
        OkHttpClient.Builder()
            .apply {
                if (BuildConfig.BUILD_TYPE != RELEASE_TYPE) {
                    val httpLoggingInterceptor = HttpLoggingInterceptor()
                    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                    addInterceptor(httpLoggingInterceptor)
                }
                writeTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
                callTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
                connectTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
                readTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
            }
            .build()

    @Provides
    @OAuth
    fun provideOAuthOkHttp(oAuth2Interceptor: OAuth2Interceptor): OkHttpClient =
        OkHttpClient.Builder()
            .apply {
                addInterceptor(oAuth2Interceptor)
                if (BuildConfig.BUILD_TYPE != RELEASE_TYPE) {
                    val httpLoggingInterceptor = HttpLoggingInterceptor()
                    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                    addInterceptor(httpLoggingInterceptor)
                }
                writeTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
                callTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
                connectTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
                readTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
            }
            .build()

    @Singleton
    @Provides
    @Default
    fun provideDefaultRetrofit(
        @Default okHttpClient: OkHttpClient,
        moshi: Moshi,
        errorAdapterFactory: RxErrorCallAdapterFactory
    ): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(errorAdapterFactory)
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .build()


    @Singleton
    @Provides
    @OAuth
    fun provideOAuthRetrofit(
        @OAuth okHttpClient: OkHttpClient,
        moshi: Moshi,
        errorAdapterFactory: RxErrorCallAdapterFactory
    ): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(errorAdapterFactory)
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .build()

    @Singleton
    @Provides
    fun provideMoshi(): Moshi = MoshiProvider.moshi

}

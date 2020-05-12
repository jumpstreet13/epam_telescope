package com.abocha.epamtelescope.data.network.di

import com.abocha.epamtelescope.data.network.services.AuthService
import com.abocha.epamtelescope.data.network.services.ChangePasswordService
import com.abocha.epamtelescope.data.network.services.ProfileService
import com.abocha.epamtelescope.data.network.services.DeezerService
import com.abocha.epamtelescope.data.network.services.SurveyService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
internal object ServiceModule {

    @Singleton
    @Provides
    fun provideAuthService(@Default retrofit: Retrofit): AuthService = retrofit.create()

    @Singleton
    @Provides
    fun provideChangePasswordService(@OAuth retrofit: Retrofit): ChangePasswordService =
        retrofit.create()

    @Singleton
    @Provides
    fun provideProfileService(@OAuth retrofit: Retrofit): ProfileService =
        retrofit.create()

    @Singleton
    @Provides
    fun provideMainService(@OAuth retrofit: Retrofit): DeezerService =
        retrofit.create()

    @Singleton
    @Provides
    fun provideSurveyService(@OAuth retrofit: Retrofit): SurveyService =
        retrofit.create()
}

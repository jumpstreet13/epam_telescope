package com.abocha.epamtelescope.di

import com.abocha.epamtelescope.initializers.AppInitializer
import com.abocha.epamtelescope.initializers.RxPaparazzoInitializer
import com.abocha.epamtelescope.initializers.ThreeTenInitializer
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet

@Module
interface InitializersModule {

    @IntoSet
    @Binds
    fun provideRxPaparazzoInitializer(impl: RxPaparazzoInitializer): AppInitializer

    @IntoSet
    @Binds
    fun provideThreeTenInitializer(impl: ThreeTenInitializer): AppInitializer
}

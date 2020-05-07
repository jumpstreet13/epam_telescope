package com.abocha.epamtelescope.data.repositories.di

import com.abocha.epamtelescope.data.network.cleaner.DataCleaner
import com.abocha.epamtelescope.data.network.cleaner.DataCleanerImpl
import com.abocha.epamtelescope.data.repositories.SongRepository
import com.epamtelescope.repository.SongGateway
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

/**
 * Created by Oleg Sheliakin on 2019-12-23.
 * Contact me by email - olegsheliakin@gmail.com
 */
@Suppress("ComplexInterface", "TooManyFunctions")
@Module
interface GatewayModule {

    @Singleton
    @Binds
    fun provideDataCleaner(cleaner: DataCleanerImpl): DataCleaner

    @Singleton
    @Binds
    fun bindSongRepo(impl: SongRepository): SongGateway
}

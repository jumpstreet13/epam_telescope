package com.abocha.epamtelescope.presentation.musiclist.di

import androidx.lifecycle.ViewModel
import com.abocha.epamtelescope.common.di.ViewModelKey
import com.abocha.epamtelescope.presentation.musiclist.MusicListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * @author Magomedov Abakar
 */
@Module
interface MusicListModule {

    @Binds
    @IntoMap
    @ViewModelKey(MusicListViewModel::class)
    fun provideViewModel(impl: MusicListViewModel): ViewModel
}
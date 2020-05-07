package com.abocha.epamtelescope.presentation.roots.di

import androidx.lifecycle.ViewModel
import com.abocha.epamtelescope.common.di.ViewModelKey
import com.abocha.epamtelescope.presentation.roots.EntranceViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * @author Magomedov Abakar
 */
@Module
interface EntranceModule {

    @Binds
    @IntoMap
    @ViewModelKey(EntranceViewModel::class)
    fun provideViewModel(impl: EntranceViewModel): ViewModel
}
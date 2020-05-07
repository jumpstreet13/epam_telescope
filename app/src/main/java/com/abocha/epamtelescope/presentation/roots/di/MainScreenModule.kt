package com.abocha.epamtelescope.presentation.roots.di

import androidx.lifecycle.ViewModel
import com.abocha.epamtelescope.common.di.ViewModelKey
import com.abocha.epamtelescope.presentation.roots.main.MainScreenViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * @author Magomedov Abakar
 */
@Module
interface MainScreenModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainScreenViewModel::class)
    fun provideNavigationDrawerViewModel(impl: MainScreenViewModel): ViewModel
}
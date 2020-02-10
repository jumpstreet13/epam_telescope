package com.abocha.epamtelescope.common.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.Factory
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module
class ViewModelFactoryModule {

    @Provides
    fun provideViewModelFactory(viewModelProviders: MutableMap<Class<out ViewModel>, Provider<ViewModel>>): Factory =
        DaggerViewModelFactory(
            viewModelProviders
        )

    private class DaggerViewModelFactory(
        private val viewModelProviders: MutableMap<Class<out ViewModel>, Provider<ViewModel>>
    ) : Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            val viewModelProvider = viewModelProviders[modelClass]
            return viewModelProvider!!.get() as T
        }
    }
}

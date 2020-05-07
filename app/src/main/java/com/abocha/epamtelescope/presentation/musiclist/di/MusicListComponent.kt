package com.abocha.epamtelescope.presentation.musiclist.di

import com.abocha.epamtelescope.common.di.InjectionComponent
import com.abocha.epamtelescope.common.di.PerFragment
import com.abocha.epamtelescope.common.di.ViewModelFactoryModule
import com.abocha.epamtelescope.di.ApplicationApi
import com.abocha.epamtelescope.di.NavigationApi
import com.abocha.epamtelescope.di.applicationApi
import com.abocha.epamtelescope.di.mainApi
import com.abocha.epamtelescope.di.navigationApi
import com.abocha.epamtelescope.presentation.musiclist.MusicListFragment
import com.abocha.epamtelescope.presentation.roots.di.MainApi
import dagger.BindsInstance
import dagger.Component

/**
 * @author Magomedov Abakar
 */
@PerFragment
@Component(
    dependencies = [ApplicationApi::class, MainApi::class, NavigationApi::class],
    modules = [ViewModelFactoryModule::class, MusicListModule::class]
)
interface MusicListComponent : InjectionComponent<MusicListFragment> {

    @Component.Factory
    interface Factory {
        fun create(
            api: ApplicationApi,
            navigationApi: NavigationApi,
            mainApi: MainApi,
            @BindsInstance fragment: MusicListFragment
        ): MusicListComponent
    }

    companion object {
        operator fun invoke(fragment: MusicListFragment) {
            DaggerMusicListComponent.factory()
                .create(
                    fragment.applicationApi(),
                    fragment.navigationApi(),
                    fragment.mainApi(),
                    fragment
                )
                .inject(fragment)
        }
    }
}
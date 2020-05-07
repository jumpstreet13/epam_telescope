package com.abocha.epamtelescope.presentation.roots.di

import com.abocha.epamtelescope.common.di.InjectionComponent
import com.abocha.epamtelescope.common.di.PerActivity
import com.abocha.epamtelescope.common.di.ViewModelFactoryModule
import com.abocha.epamtelescope.di.ApplicationApi
import com.abocha.epamtelescope.di.applicationApi
import com.abocha.epamtelescope.presentation.roots.main.MainScreenActivity
import dagger.BindsInstance
import dagger.Component

/**
 * @author Magomedov Abakar
 */
@PerActivity
@Component(
    dependencies = [ApplicationApi::class],
    modules = [ViewModelFactoryModule::class, MainScreenModule::class]
)
interface MainScreenComponent : InjectionComponent<MainScreenActivity>,
    MainApi {

    @Component.Factory
    interface Factory {
        fun create(api: ApplicationApi, @BindsInstance activity: MainScreenActivity): MainScreenComponent
    }

    companion object {
        operator fun invoke(activity: MainScreenActivity): MainScreenComponent =
            DaggerMainScreenComponent.factory()
                .create(activity.applicationApi(), activity)
    }
}
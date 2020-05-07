package com.abocha.epamtelescope.presentation.roots.di

import com.abocha.epamtelescope.common.di.InjectionComponent
import com.abocha.epamtelescope.common.di.PerActivity
import com.abocha.epamtelescope.common.di.ViewModelFactoryModule
import com.abocha.epamtelescope.di.ApplicationApi
import com.abocha.epamtelescope.di.applicationApi
import com.abocha.epamtelescope.presentation.roots.EntranceActivity
import dagger.BindsInstance
import dagger.Component

/**
 * @author Magomedov Abakar
 */

@PerActivity
@Component(
    dependencies = [ApplicationApi::class],
    modules = [ViewModelFactoryModule::class, EntranceModule::class]
)
interface EntranceComponent : InjectionComponent<EntranceActivity> {

    @Component.Factory
    interface Factory {
        fun create(api: ApplicationApi, @BindsInstance activity: EntranceActivity): EntranceComponent
    }

    companion object {
        operator fun invoke(activity: EntranceActivity) {
            DaggerEntranceComponent.factory()
                .create(activity.applicationApi(), activity)
                .inject(activity)
        }
    }
}
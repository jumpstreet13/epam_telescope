package com.abocha.epamtelescope.di

import android.content.Context
import com.abocha.epamtelescope.TelescopeApplication
import com.abocha.epamtelescope.common.di.InjectionComponent
import com.abocha.epamtelescope.common.di.PerApplication
import com.abocha.epamtelescope.data.di.DataApi
import dagger.BindsInstance
import dagger.Component

/**
 * Created by Oleg Sheliakin on 2019-12-23.
 * Contact me by email - olegsheliakin@gmail.com
 */
@PerApplication
@Component(
    dependencies = [DataApi::class], modules = [CoreModule::class,
        InitializersModule::class]
)
interface AppComponent : ApplicationApi, NavigationApi, InjectionComponent<TelescopeApplication> {

    @Component.Factory
    interface Factory {
        fun create(
            api: DataApi, @BindsInstance applicationContext: Context
        ): AppComponent
    }
}

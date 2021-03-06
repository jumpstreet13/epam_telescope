package com.abocha.epamtelescope

import android.app.Application
import com.abocha.epamtelescope.data.di.DaggerDataComponent
import com.abocha.epamtelescope.di.AppComponent
import com.abocha.epamtelescope.di.ApplicationApi
import com.abocha.epamtelescope.di.ApplicationApiProvider
import com.abocha.epamtelescope.di.DaggerAppComponent
import com.abocha.epamtelescope.di.NavigationApi
import com.abocha.epamtelescope.di.NavigationApiProvider
import com.abocha.epamtelescope.initializers.TelescopeInitializer
import timber.log.Timber
import javax.inject.Inject

class TelescopeApplication : Application(), ApplicationApiProvider, NavigationApiProvider {

    @Inject
    lateinit var telescopeInitializer: TelescopeInitializer

    override val navigationApi: NavigationApi
        get() = component

    override val applicationApi: ApplicationApi
        get() = component

    private val component: AppComponent by lazy {
        DaggerAppComponent.factory()
            .create(DaggerDataComponent.factory().create(this), this)
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
        telescopeInitializer.init(this)

        //FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(!BuildConfig.DEBUG)
        Timber.plant(Timber.DebugTree())
    }

}

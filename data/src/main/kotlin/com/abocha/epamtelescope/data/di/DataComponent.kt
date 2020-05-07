package com.abocha.epamtelescope.data.di

import android.content.Context
import com.abocha.epamtelescope.data.database.di.DBModule
import com.abocha.epamtelescope.data.network.di.NetworkModule
import com.abocha.epamtelescope.data.network.di.OAuth2TokenModule
import com.abocha.epamtelescope.data.network.di.ServiceModule
import com.abocha.epamtelescope.data.repositories.di.GatewayModule
import com.abocha.epamtelescope.data.repositories.di.SurveyTasksModule
import com.abocha.epamtelescope.data.workers.di.WorkersModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ServiceModule::class,
        NetworkModule::class,
        OAuth2TokenModule::class,
        GatewayModule::class,
        SurveyTasksModule::class,
        WorkersModule::class,
        DBModule::class]
)
interface DataComponent : DataApi {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): DataComponent
    }
}

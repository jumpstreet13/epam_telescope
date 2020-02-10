package com.abocha.epamtelescope.data.workers.di

import android.content.Context
import androidx.work.WorkManager
import androidx.work.WorkerFactory
import com.abocha.epamtelescope.data.workers.LoadDataWorker
import com.abocha.epamtelescope.data.workers.WorkManagerInitializer
import com.abocha.epamtelescope.data.workers.WorkManagerInitializerImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

/**
 * Created by Oleg Sheliakin on 2020-01-21.
 * Contact me by email - olegsheliakin@gmail.com
 */
@Module
abstract class WorkersModule {

    @Binds
    abstract fun provideWorkerManagerInitializer(impl: WorkManagerInitializerImpl): WorkManagerInitializer

    @Binds
    abstract fun provideWorkerFactory(impl: DefaultWorkerFactory): WorkerFactory

    @Binds
    @IntoMap
    @WorkerKey(LoadDataWorker::class)
    abstract fun provideLoadDataWorkerCreator(impl: LoadDataWorker.Creator): WorkerCreator

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun provideWorkManager(appContext: Context): WorkManager =
            WorkManager.getInstance(appContext)

    }
}

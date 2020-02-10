package com.abocha.epamtelescope.data.workers

import android.content.Context
import androidx.work.Configuration
import androidx.work.WorkManager
import androidx.work.WorkerFactory
import javax.inject.Inject

/**
 * Created by Oleg Sheliakin on 2020-01-21.
 * Contact me by email - olegsheliakin@gmail.com
 */
class WorkManagerInitializerImpl @Inject constructor(
    private val workerFactory: WorkerFactory
) : WorkManagerInitializer {
    override fun init(appContext: Context) {
        WorkManager.initialize(
            appContext,
            Configuration.Builder().setWorkerFactory(workerFactory).build()
        )
    }

}

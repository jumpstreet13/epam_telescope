package com.abocha.epamtelescope.data.workers.di

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import javax.inject.Inject
import javax.inject.Provider

/**
 * Created by Oleg Sheliakin on 2020-01-21.
 * Contact me by email - olegsheliakin@gmail.com
 */
class DefaultWorkerFactory @Inject constructor(
    private val workerFactories: Map<Class<out ListenableWorker>, @JvmSuppressWildcards Provider<WorkerCreator>>
) : WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        val foundedEntry =
            workerFactories.entries.find { Class.forName(workerClassName).isAssignableFrom(it.key) }
        val provider = foundedEntry?.value
            ?: throw IllegalStateException("Cannot find work creator for ${workerClassName}")
        return provider.get().create(appContext, workerParameters)
    }
}

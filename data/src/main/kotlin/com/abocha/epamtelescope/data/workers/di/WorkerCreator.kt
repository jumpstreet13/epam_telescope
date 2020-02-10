package com.abocha.epamtelescope.data.workers.di

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerParameters

/**
 * Created by Oleg Sheliakin on 2020-01-21.
 * Contact me by email - olegsheliakin@gmail.com
 */
interface WorkerCreator {
    fun create(appContext: Context, workerParameters: WorkerParameters): ListenableWorker
}

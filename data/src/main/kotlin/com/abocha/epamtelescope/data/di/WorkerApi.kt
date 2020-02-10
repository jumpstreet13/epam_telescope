package com.abocha.epamtelescope.data.di

import com.abocha.epamtelescope.data.workers.WorkManagerInitializer

/**
 * Created by Oleg Sheliakin on 2020-01-21.
 * Contact me by email - olegsheliakin@gmail.com
 */
interface WorkerApi {
    fun provideWorkerInitializer(): WorkManagerInitializer
}

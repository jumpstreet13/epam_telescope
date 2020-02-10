package com.abocha.epamtelescope.data.workers.di

import androidx.work.ListenableWorker
import dagger.MapKey
import kotlin.reflect.KClass

/**
 * Created by Oleg Sheliakin on 2020-01-21.
 * Contact me by email - olegsheliakin@gmail.com
 */
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class WorkerKey(val value: KClass<out ListenableWorker>)

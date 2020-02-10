package com.abocha.epamtelescope.data.workers

import android.content.Context
import android.util.Log
import androidx.work.ListenableWorker
import androidx.work.RxWorker
import androidx.work.WorkerParameters
import com.abocha.epamtelescope.data.workers.di.WorkerCreator
import com.abocha.epamtelescope.repository.AssignmentsRepository
import com.abocha.epamtelescope.repository.DictionariesRepository
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import javax.inject.Inject

/**
 * Created by Oleg Sheliakin on 2020-01-21.
 * Contact me by email - olegsheliakin@gmail.com
 */
class LoadDataWorker @Inject constructor(
    applicationContext: Context,
    private val workerParameters: WorkerParameters,
    private val assignmentsRepository: AssignmentsRepository,
    private val dictionariesRepository: DictionariesRepository
) : RxWorker(applicationContext, workerParameters) {

    override fun createWork(): Single<Result> =
        Single
            .zip(
                assignmentsRepository.refresh()
                    .toSingleDefault(Result.success())
                    .onErrorReturnItem(Result.retry()),
                dictionariesRepository.refresh()
                    .toSingleDefault(Result.success())
                    .onErrorReturnItem(Result.retry()),
                BiFunction<Result, Result, Result> { t1, t2 ->
                    listOf(t1, t2).firstOrNull { it is Result.Retry } ?: Result.success()
                }
            )
            .doOnSubscribe {
                Log.d(
                    LoadDataWorker::class.java.simpleName,
                    "tags ${workerParameters.tags}start ${hashCode()}"
                )
            }
            .doOnError { Log.d(LoadDataWorker::class.java.simpleName, "error $it") }
            .doOnSuccess {
                Log.d(LoadDataWorker::class.java.simpleName, "complete")
            }

    companion object {
        const val RETRY_TAG = "gir_retry_load_data_worker"
        const val PERIODIC_TAG = "gir_periodic_load_data_worker"
    }

    class Creator @Inject constructor(
        private val assignmentsRepository: AssignmentsRepository,
        private val dictionariesRepository: DictionariesRepository
    ) : WorkerCreator {
        override fun create(
            appContext: Context,
            workerParameters: WorkerParameters
        ): ListenableWorker =
            LoadDataWorker(
                appContext,
                workerParameters,
                assignmentsRepository,
                dictionariesRepository
            )

    }

}

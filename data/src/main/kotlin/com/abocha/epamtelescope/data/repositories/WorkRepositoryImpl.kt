package com.abocha.epamtelescope.data.repositories

import androidx.work.*
import com.abocha.epamtelescope.data.workers.LoadDataWorker
import com.abocha.epamtelescope.repository.WorkRepository
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by Oleg Sheliakin on 2020-01-21.
 * Contact me by email - olegsheliakin@gmail.com
 */
class WorkRepositoryImpl @Inject constructor(
    private val workManager: WorkManager
) : WorkRepository {
    override fun startPeriodicRefreshData() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val periodicRequest =
            PeriodicWorkRequestBuilder<LoadDataWorker>(
                REFRESH_DATA_PERIOD,
                TimeUnit.HOURS,
                REFRESH_DATA_PERIOD / 2,
                TimeUnit.HOURS
            )
                .addTag(LoadDataWorker.PERIODIC_TAG)
                .setConstraints(constraints)
                .build()

        workManager.enqueueUniquePeriodicWork(
            LoadDataWorker.PERIODIC_TAG,
            ExistingPeriodicWorkPolicy.KEEP,
            periodicRequest
        )
    }

    companion object {
        private const val REFRESH_DATA_PERIOD = 3L
    }

}

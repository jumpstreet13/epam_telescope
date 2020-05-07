package com.abocha.epamtelescope.data.repositories

import androidx.work.*
import com.abocha.epamtelescope.data.database.AppDatabase
import com.abocha.epamtelescope.data.database.daos.AssignmentDao
import com.abocha.epamtelescope.data.database.daos.PlatformsDao
import com.abocha.epamtelescope.data.database.daos.WellSlotDao
import com.abocha.epamtelescope.data.database.entities.toDbEntities
import com.abocha.epamtelescope.data.database.entities.toDomainModels
import com.abocha.epamtelescope.data.network.models.responses.toDbEntities
import com.abocha.epamtelescope.data.network.services.MainService
import com.abocha.epamtelescope.data.workers.LoadDataWorker
import com.abocha.epamtelescope.entities.Assignment
import com.abocha.epamtelescope.repository.AssignmentsRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import java.io.IOException
import javax.inject.Inject

/**
 * Created by Oleg Sheliakin on 2020-01-14.
 * Contact me by email - olegsheliakin@gmail.com
 */
class AssignmentsRepositoryImpl @Inject constructor(
    private val assignmentDao: AssignmentDao,
    private val platformsDao: PlatformsDao,
    private val wellSlotDao: WellSlotDao,
    private val database: AppDatabase,
    private val mainService: MainService,
    private val workManager: WorkManager
) : AssignmentsRepository {

    override fun streamAllAssignments(): Flowable<List<Assignment>> =
        assignmentDao.streamAllAssignments()
            .map { it.toDomainModels() }

    override fun refresh(): Completable =
        mainService.getAssignments()
            .doOnSuccess {
                val platforms =
                    it.flatMap { assignment -> assignment.platforms.toDbEntities(assignment.id) }
                val wellSlots =
                    it.flatMap { assignment -> assignment.platforms }
                        .flatMap { platform -> platform.wellSlots.toDbEntities(platform.id) }
                database.runInTransaction {
                    assignmentDao.replaceAll(it.toDbEntities())
                    platformsDao.replaceAll(platforms)
                    wellSlotDao.replaceAll(wellSlots)
                }
            }
            .doOnError {
                if (it is IOException) {
                    startRetryWork()
                }
            }
            .ignoreElement()

    private fun startRetryWork() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val oneTimeRequest = OneTimeWorkRequestBuilder<LoadDataWorker>()
            .setConstraints(constraints)
            .build()

        workManager.enqueueUniqueWork(
            LoadDataWorker.RETRY_TAG,
            ExistingWorkPolicy.KEEP,
            oneTimeRequest
        )
    }

}

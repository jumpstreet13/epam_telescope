package com.abocha.epamtelescope.data.repositories

import com.abocha.epamtelescope.data.database.daos.RisersDao
import com.abocha.epamtelescope.data.database.entities.toDbModel
import com.abocha.epamtelescope.data.database.entities.toDomainModel
import com.abocha.epamtelescope.entities.Riser
import com.abocha.epamtelescope.entities.SurveyTask
import com.abocha.epamtelescope.entities.SurveyType
import com.abocha.epamtelescope.entities.SyncState
import com.abocha.epamtelescope.repository.RiserRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Oleg Sheliakin on 2020-01-27.
 * Contact me by email - olegsheliakin@gmail.com
 */
class RisersRepositoryImpl @Inject constructor(
    private val dao: RisersDao
) : RiserRepository {

    override fun streamSurveyTasks(platformId: Long): Flowable<List<SurveyTask>> =
        dao.streamAll(platformId)
            .map {
                it.map { entity ->
                    entity.toDomainModel(surveyType = SurveyType.RISER)
                }
            }

    override fun save(riser: Riser, isDraft: Boolean): Completable =
        Completable.fromAction {
            dao.insertOrReplace(riser.toDbModel(SyncState.DRAFTED))
        }

    override fun getById(surveyId: Long): Single<Riser> =
        dao.getById(surveyId)
            .map { it.toDomainModel() }
}

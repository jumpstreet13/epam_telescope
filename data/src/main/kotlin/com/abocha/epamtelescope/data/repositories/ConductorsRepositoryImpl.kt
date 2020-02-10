package com.abocha.epamtelescope.data.repositories

import com.abocha.epamtelescope.data.database.daos.ConductorsDao
import com.abocha.epamtelescope.data.database.daos.WellSlotDao
import com.abocha.epamtelescope.data.database.entities.toDbModel
import com.abocha.epamtelescope.data.database.entities.toDomainModel
import com.abocha.epamtelescope.entities.ConductorWellHead
import com.abocha.epamtelescope.entities.SurveyTask
import com.abocha.epamtelescope.entities.SurveyType
import com.abocha.epamtelescope.entities.SyncState
import com.abocha.epamtelescope.repository.ConductorRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Oleg Sheliakin on 2020-01-27.
 * Contact me by email - olegsheliakin@gmail.com
 */
class ConductorsRepositoryImpl @Inject constructor(
    private val dao: ConductorsDao,
    private val wellSlotDao: WellSlotDao
) : ConductorRepository {

    override fun streamSurveyTasks(platformId: Long): Flowable<List<SurveyTask>> =
        dao.streamAll(platformId)
            .map {
                it.map { entity ->
                    entity.toDomainModel(surveyType = SurveyType.CONDUCTOR_AND_WELLHEAD)
                }
            }

    override fun save(
        conductorWellHead: ConductorWellHead,
        isDraft: Boolean
    ): Completable =
        Completable.fromAction {
            dao.insertOrReplace(conductorWellHead.toDbModel(SyncState.DRAFTED))
        }

    override fun getById(surveyId: Long): Single<ConductorWellHead> =
        dao.getById(surveyId)
            .flatMap { entity ->
                if (entity.slotId == null) {
                    Single.just(entity to null)
                } else {
                    wellSlotDao.getByWellSlotId(entity.slotId)
                        .map { entity to it }
                }
            }
            .map { it.first.toDomainModel(it.second) }
}

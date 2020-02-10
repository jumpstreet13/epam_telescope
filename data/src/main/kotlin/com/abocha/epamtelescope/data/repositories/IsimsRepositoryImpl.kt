package com.abocha.epamtelescope.data.repositories

import com.abocha.epamtelescope.data.database.daos.IsimsDao
import com.abocha.epamtelescope.data.database.entities.toDbModel
import com.abocha.epamtelescope.data.database.entities.toDomainModel
import com.abocha.epamtelescope.entities.Isims
import com.abocha.epamtelescope.entities.SurveyTask
import com.abocha.epamtelescope.entities.SurveyType
import com.abocha.epamtelescope.entities.SyncState
import com.abocha.epamtelescope.repository.IsimsRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Oleg Sheliakin on 2020-01-27.
 * Contact me by email - olegsheliakin@gmail.com
 */
class IsimsRepositoryImpl @Inject constructor(
    private val dao: IsimsDao
) : IsimsRepository {

    override fun streamSurveyTasks(platformId: Long): Flowable<List<SurveyTask>> =
        dao.streamAll(platformId)
            .map {
                it.map { entity ->
                    entity.toDomainModel(surveyType = SurveyType.ISIMS)
                }
            }

    override fun save(
        isims: Isims,
        isDraft: Boolean
    ): Completable =
        Completable.fromAction {
            dao.insertOrReplace(isims.toDbModel(SyncState.DRAFTED))
        }

    override fun getById(surveyId: Long): Single<Isims> =
        dao.getById(surveyId)
            .map { it.toDomainModel() }
}

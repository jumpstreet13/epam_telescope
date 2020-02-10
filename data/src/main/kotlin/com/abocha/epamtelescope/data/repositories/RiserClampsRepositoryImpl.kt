package com.abocha.epamtelescope.data.repositories

import com.abocha.epamtelescope.data.database.daos.RiserClampsDao
import com.abocha.epamtelescope.data.database.entities.toDbModel
import com.abocha.epamtelescope.data.database.entities.toDomainModel
import com.abocha.epamtelescope.data.network.models.requests.toRequest
import com.abocha.epamtelescope.data.network.services.SurveyService
import com.abocha.epamtelescope.entities.RiserClamp
import com.abocha.epamtelescope.entities.SurveyTask
import com.abocha.epamtelescope.entities.SurveyType
import com.abocha.epamtelescope.entities.SyncState
import com.abocha.epamtelescope.repository.RiserClampRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Oleg Sheliakin on 2020-01-27.
 * Contact me by email - olegsheliakin@gmail.com
 */
class RiserClampsRepositoryImpl @Inject constructor(
    private val surveyService: SurveyService,
    private val riserClampsDao: RiserClampsDao
) : RiserClampRepository {

    override fun streamSurveyTasks(platformId: Long): Flowable<List<SurveyTask>> =
        riserClampsDao.streamAll(platformId)
            .map {
                it.map { entity ->
                    entity.toDomainModel(surveyType = SurveyType.RISER_CLAMPS)
                }
            }

    override fun save(riserClampSurvey: RiserClamp, isDraft: Boolean): Completable =
        if (isDraft) {
            Completable.fromAction {
                val syncState = SyncState.DRAFTED

                val riserClampsDbModel = riserClampSurvey.toDbModel(syncState)
                riserClampsDao.insertOrReplace(riserClampsDbModel)
            }
        } else {
            surveyService.sendRiseClamp(riserClampSurvey.toRequest())
        }

    override fun getById(surveyId: Long): Single<RiserClamp> =
        riserClampsDao.getById(surveyId)
            .map { it.toDomainModel() }
}

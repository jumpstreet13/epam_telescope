package com.abocha.epamtelescope.data.repositories

import com.abocha.epamtelescope.data.database.daos.CathodicProtectionCalibrationsDao
import com.abocha.epamtelescope.data.database.entities.toDbModel
import com.abocha.epamtelescope.data.database.entities.toDomainModel
import com.abocha.epamtelescope.entities.CPCalibration
import com.abocha.epamtelescope.entities.SurveyTask
import com.abocha.epamtelescope.entities.SurveyType
import com.abocha.epamtelescope.entities.SyncState
import com.abocha.epamtelescope.repository.CPCalibrationRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Oleg Sheliakin on 2020-01-27.
 * Contact me by email - olegsheliakin@gmail.com
 */
class CPCalibrationsRepositoryImpl @Inject constructor(
    private val dao: CathodicProtectionCalibrationsDao
) : CPCalibrationRepository {

    override fun streamSurveyTasks(platformId: Long): Flowable<List<SurveyTask>> =
        dao.streamAll(platformId)
            .map {
                it.map { entity ->
                    entity.toDomainModel(surveyType = SurveyType.CATHODIC_PROTECTION_CALIBRATION)
                }
            }

    override fun save(
        cathodicProtectionCalibration: CPCalibration,
        isDraft: Boolean
    ): Completable =
        Completable.fromAction {
            dao.insertOrReplace(cathodicProtectionCalibration.toDbModel(SyncState.DRAFTED))
        }

    override fun getById(surveyId: Long): Single<CPCalibration> =
        dao.getById(surveyId)
            .map { it.toDomainModel() }
}

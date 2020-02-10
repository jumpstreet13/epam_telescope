package com.abocha.epamtelescope.data.repositories

import com.abocha.epamtelescope.data.database.daos.CathodicProtectionsDao
import com.abocha.epamtelescope.data.database.entities.toDbModel
import com.abocha.epamtelescope.data.database.entities.toDomainModel
import com.abocha.epamtelescope.entities.CathodicProtection
import com.abocha.epamtelescope.entities.SurveyTask
import com.abocha.epamtelescope.entities.SurveyType
import com.abocha.epamtelescope.entities.SyncState
import com.abocha.epamtelescope.repository.CathodicProtectionRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Oleg Sheliakin on 2020-01-27.
 * Contact me by email - olegsheliakin@gmail.com
 */
class CathodicProtectionsRepositoryImpl @Inject constructor(
    private val dao: CathodicProtectionsDao
) : CathodicProtectionRepository {

    override fun streamSurveyTasks(platformId: Long): Flowable<List<SurveyTask>> =
        dao.streamAll(platformId)
            .map {
                it.map { entity ->
                    entity.toDomainModel(
                        surveyType = SurveyType.CATHODIC_PROTECTION
                    )
                }
            }

    override fun save(
        cathodicProtection: CathodicProtection,
        isDraft: Boolean
    ): Completable =
        Completable.fromAction {
            dao.insertOrReplace(cathodicProtection.toDbModel(SyncState.DRAFTED))
        }

    override fun getById(surveyId: Long): Single<CathodicProtection> =
        dao.getById(surveyId)
            .map { it.toDomainModel() }
}

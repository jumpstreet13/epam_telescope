package com.abocha.epamtelescope.data.repositories

import com.abocha.epamtelescope.data.database.daos.PlatfromPhotosDao
import com.abocha.epamtelescope.data.database.entities.toDbModel
import com.abocha.epamtelescope.data.database.entities.toDomainModel
import com.abocha.epamtelescope.entities.*
import com.abocha.epamtelescope.repository.PlatformPhotosRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by Oleg Sheliakin on 2020-01-27.
 * Contact me by email - olegsheliakin@gmail.com
 */
class PlatfromPhotosRepositoryImpl @Inject constructor(
    private val dao: PlatfromPhotosDao
) : PlatformPhotosRepository {

    override fun streamSurveyTasks(platformId: Long): Flowable<List<SurveyTask>> =
        dao.streamAll(platformId)
            .map {
                it.map { entity ->
                    entity.toDomainModel(surveyType = SurveyType.OVERALL_PLATFORM_PHOTOS)
                }
            }

    override fun save(
        platfromPhotos: PlatfromPhotos,
        isDraft: Boolean
    ): Completable =
        Completable.fromAction {
            dao.insertOrReplace(platfromPhotos.toDbModel(SyncState.DRAFTED))
        }

}

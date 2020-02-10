package com.abocha.epamtelescope.data.repositories

import com.abocha.epamtelescope.data.database.daos.PlatformsDao
import com.abocha.epamtelescope.data.database.entities.toDomainModels
import com.abocha.epamtelescope.entities.Platform
import com.abocha.epamtelescope.repository.PlatformsRepository
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by Oleg Sheliakin on 2020-01-14.
 * Contact me by email - olegsheliakin@gmail.com
 */
class PlatformsRepositoryImpl @Inject constructor(
    private val platformsDao: PlatformsDao
) : PlatformsRepository {

    override fun streamPlatforms(assignmentId: Long): Flowable<List<Platform>> =
        platformsDao.streamAllPlatforms(assignmentId)
            .map { it.toDomainModels() }

}

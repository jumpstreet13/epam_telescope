package com.abocha.epamtelescope.usecases

import com.abocha.epamtelescope.entities.Platform
import com.abocha.epamtelescope.repository.PlatformsRepository
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by Oleg Sheliakin on 2020-01-14.
 * Contact me by email - olegsheliakin@gmail.com
 */
class GetAllPlatformsUseCase @Inject constructor(private val platformsRepository: PlatformsRepository) {

    fun execute(assignmentId: Long): Flowable<List<Platform>> =
        platformsRepository.streamPlatforms(assignmentId)

}

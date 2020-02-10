package com.abocha.epamtelescope.repository

import com.abocha.epamtelescope.entities.Platform
import io.reactivex.Flowable

/**
 * Created by Oleg Sheliakin on 2020-01-14.
 * Contact me by email - olegsheliakin@gmail.com
 */
interface PlatformsRepository {

    fun streamPlatforms(assignmentId: Long): Flowable<List<Platform>>
}

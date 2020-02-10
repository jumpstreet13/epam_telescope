package com.abocha.epamtelescope.repository

import com.abocha.epamtelescope.entities.PlatfromPhotos
import io.reactivex.Completable

/**
 * Created by Oleg Sheliakin on 2020-01-24.
 * Contact me by email - olegsheliakin@gmail.com
 */
interface PlatformPhotosRepository : SurveyTasksRepository {
    fun save(platfromPhotos: PlatfromPhotos, isDraft: Boolean): Completable
}

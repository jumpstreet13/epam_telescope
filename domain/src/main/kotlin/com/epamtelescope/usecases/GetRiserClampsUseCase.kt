package com.abocha.epamtelescope.usecases

import com.abocha.epamtelescope.entities.RiserClamp
import com.abocha.epamtelescope.repository.RiserClampRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Oleg Sheliakin on 2020-01-24.
 * Contact me by email - olegsheliakin@gmail.com
 */
class GetRiserClampsUseCase @Inject constructor(
    private val riserClampRepository: RiserClampRepository
) {

    fun execute(surveyId: Long): Single<RiserClamp> =
        riserClampRepository.getById(surveyId)

}

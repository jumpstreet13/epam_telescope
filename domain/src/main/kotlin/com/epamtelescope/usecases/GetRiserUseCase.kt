package com.abocha.epamtelescope.usecases

import com.abocha.epamtelescope.entities.Riser
import com.abocha.epamtelescope.repository.RiserRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Oleg Sheliakin on 2020-01-24.
 * Contact me by email - olegsheliakin@gmail.com
 */
class GetRiserUseCase @Inject constructor(
    private val riserRepository: RiserRepository
) {

    fun execute(surveyId: Long): Single<Riser> =
        riserRepository.getById(surveyId)

}

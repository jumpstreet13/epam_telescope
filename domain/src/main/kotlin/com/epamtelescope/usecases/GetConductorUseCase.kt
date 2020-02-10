package com.abocha.epamtelescope.usecases

import com.abocha.epamtelescope.entities.ConductorWellHead
import com.abocha.epamtelescope.repository.ConductorRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Oleg Sheliakin on 2020-01-24.
 * Contact me by email - olegsheliakin@gmail.com
 */
class GetConductorUseCase @Inject constructor(
    private val conductorRepository: ConductorRepository
) {

    fun execute(surveyId: Long): Single<ConductorWellHead> =
        conductorRepository.getById(surveyId)

}

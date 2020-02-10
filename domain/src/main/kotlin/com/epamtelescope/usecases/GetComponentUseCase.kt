package com.abocha.epamtelescope.usecases

import com.abocha.epamtelescope.entities.Component
import com.abocha.epamtelescope.repository.ComponentRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Oleg Sheliakin on 2020-01-24.
 * Contact me by email - olegsheliakin@gmail.com
 */
class GetComponentUseCase @Inject constructor(
    private val componentRepository: ComponentRepository
) {

    fun execute(surveyId: Long): Single<Component> =
        componentRepository.getById(surveyId)

}

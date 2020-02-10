package com.abocha.epamtelescope.usecases

import com.abocha.epamtelescope.entities.Isims
import com.abocha.epamtelescope.repository.IsimsRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Oleg Sheliakin on 2020-01-24.
 * Contact me by email - olegsheliakin@gmail.com
 */
class GetIsimsUseCase @Inject constructor(
    private val isimsRepository: IsimsRepository
) {

    fun execute(surveyId: Long): Single<Isims> =
        isimsRepository.getById(surveyId)

}

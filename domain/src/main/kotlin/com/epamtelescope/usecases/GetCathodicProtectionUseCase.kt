package com.abocha.epamtelescope.usecases

import com.abocha.epamtelescope.entities.CathodicProtection
import com.abocha.epamtelescope.repository.CathodicProtectionRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Oleg Sheliakin on 2020-01-24.
 * Contact me by email - olegsheliakin@gmail.com
 */
class GetCathodicProtectionUseCase @Inject constructor(
    private val cathodicProtectionRepository: CathodicProtectionRepository
) {

    fun execute(surveyId: Long): Single<CathodicProtection> =
        cathodicProtectionRepository.getById(surveyId)

}

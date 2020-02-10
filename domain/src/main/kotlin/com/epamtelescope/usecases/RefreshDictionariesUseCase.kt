package com.abocha.epamtelescope.usecases

import com.abocha.epamtelescope.repository.DictionariesRepository
import io.reactivex.Completable
import javax.inject.Inject

/**
 * Created by Oleg Sheliakin on 2020-01-14.
 * Contact me by email - olegsheliakin@gmail.com
 */
class RefreshDictionariesUseCase @Inject constructor(
    private val dictionariesRepository: DictionariesRepository
) {

    fun execute(): Completable =
        dictionariesRepository.refresh()
}

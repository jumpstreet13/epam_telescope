package com.abocha.epamtelescope.usecases

import com.abocha.epamtelescope.entities.Dictionary
import com.abocha.epamtelescope.entities.DictionaryType
import com.abocha.epamtelescope.entities.find
import com.abocha.epamtelescope.repository.DictionariesRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Oleg Sheliakin on 2020-01-23.
 * Contact me by email - olegsheliakin@gmail.com
 */
class GetDictionariesUseCase @Inject constructor(
    private val dictionariesRepository: DictionariesRepository
) {

    fun execute(dictionaryType: DictionaryType): Observable<List<Dictionary>> =
        dictionariesRepository.getDictionary()
            .map {
                it.find(dictionaryType)
            }

}

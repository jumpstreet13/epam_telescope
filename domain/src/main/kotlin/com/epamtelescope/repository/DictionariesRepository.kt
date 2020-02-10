package com.abocha.epamtelescope.repository

import com.abocha.epamtelescope.entities.Dictionaries
import io.reactivex.Completable
import io.reactivex.Observable

interface DictionariesRepository {

    fun refresh(): Completable

    fun getDictionary() : Observable<Dictionaries>
}

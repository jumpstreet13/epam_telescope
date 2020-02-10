package com.abocha.epamtelescope.data.network.cleaner

import io.reactivex.Completable

interface DataCleaner {
    fun clean(): Completable
}

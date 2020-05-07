package com.epamtelescope.repository

import com.epamtelescope.entities.Song
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * @author Magomedov Abakar
 */
interface SongGateway {
    fun streamAllSongs(): Flowable<List<Song>>
    fun refresh(): Completable
}
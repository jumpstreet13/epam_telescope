package com.epamtelescope.usecases

import com.epamtelescope.entities.Song
import com.epamtelescope.repository.SongGateway
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * @author Magomedov Abakar
 */
class ObserveAllSongsUseCase @Inject constructor(private val songGateway: SongGateway) {

    fun execute(): Flowable<List<Song>> =
        songGateway.streamAllSongs()
}
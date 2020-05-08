package com.abocha.epamtelescope.data.repositories

import com.abocha.epamtelescope.data.database.AppDatabase
import com.abocha.epamtelescope.data.database.daos.SongDao
import com.abocha.epamtelescope.data.database.entities.toDomainModels
import com.abocha.epamtelescope.data.network.models.responses.toDbEntities
import com.abocha.epamtelescope.data.network.services.SpotifyService
import com.epamtelescope.entities.Song
import com.epamtelescope.repository.SongGateway
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * @author Magomedov Abakar
 */
class SongRepository @Inject constructor(
    private val songDao: SongDao,
    private val spotifyService: SpotifyService,
    private val database: AppDatabase
) : SongGateway {

    override fun streamAllSongs(): Flowable<List<Song>> =
        songDao.streamAllSongs()
            .map { it.toDomainModels() }

    override fun refresh(): Completable =
        spotifyService.getSongs()
            .doOnSuccess {
                database.runInTransaction {
                    songDao.replaceAll(it.toDbEntities())
                }
            }
            .ignoreElement()
}
package com.abocha.epamtelescope.data.repositories

import com.abocha.epamtelescope.data.database.AppDatabase
import com.abocha.epamtelescope.data.database.daos.SongDao
import com.abocha.epamtelescope.data.database.entities.toDomainModels
import com.abocha.epamtelescope.data.network.models.responses.toDbEntities
import com.abocha.epamtelescope.data.network.services.DeezerService
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
    private val deezerService: DeezerService,
    private val database: AppDatabase
) : SongGateway {

    override fun streamAllSongs(): Flowable<List<Song>> =
        songDao.streamAllSongs()
            .map { it.toDomainModels() }

    override fun requestSongList(limit: Int, offset: Int): Completable =
        deezerService.getSongs(limit = limit, offset = offset)
            .doOnSuccess {
                database.runInTransaction {
                    songDao.replaceAll(it.trackList.toDbEntities())
                }
            }
            .ignoreElement()
}
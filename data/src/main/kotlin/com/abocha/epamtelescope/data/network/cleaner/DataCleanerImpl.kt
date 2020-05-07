package com.abocha.epamtelescope.data.network.cleaner

import com.abocha.epamtelescope.data.database.AppDatabase
import com.abocha.epamtelescope.oauth2token.session.OAuth2Session
import io.reactivex.Completable
import javax.inject.Inject

class DataCleanerImpl @Inject constructor(
    private val oAuth2Session: OAuth2Session,
    private val girDatabase: AppDatabase
) : DataCleaner {

    override fun clean(): Completable =
        Completable.fromAction {
            oAuth2Session.clearToken()
            girDatabase.clearAllTables()
        }
}

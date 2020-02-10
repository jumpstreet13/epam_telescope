package com.abocha.epamtelescope.data.manager

import android.content.SharedPreferences
import com.abocha.epamtelescope.data.network.models.responses.UserProfileResponse
import com.abocha.epamtelescope.data.network.models.responses.toDomainModel
import com.abocha.epamtelescope.data.sharedpreference.JsonParser
import com.abocha.epamtelescope.data.sharedpreference.asParser
import com.abocha.epamtelescope.data.sharedpreference.delegate
import com.abocha.epamtelescope.entities.User
import com.squareup.moshi.Moshi
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileManager @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val jsonParser: ProfileJsonParser
) {
    private var currentProfile: User? by sharedPreferences.delegate(jsonParser)

    fun saveProfile(response: UserProfileResponse) {
        response.let {
            currentProfile = it.toDomainModel()
        }
    }

    fun requestProfile(): Single<User> =
        Single.fromCallable { currentProfile }

    fun clear() {
        currentProfile = null
    }
}

class ProfileJsonParser @Inject constructor(private val moshi: Moshi) :
    JsonParser<User> by moshi.asParser()

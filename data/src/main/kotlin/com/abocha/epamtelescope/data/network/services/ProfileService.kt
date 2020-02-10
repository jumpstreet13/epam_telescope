package com.abocha.epamtelescope.data.network.services

import com.abocha.epamtelescope.data.network.models.responses.UserProfileResponse
import io.reactivex.Single
import retrofit2.http.GET

interface ProfileService {

    @GET("users/account/me/")
    fun requestProfile(): Single<UserProfileResponse>
}

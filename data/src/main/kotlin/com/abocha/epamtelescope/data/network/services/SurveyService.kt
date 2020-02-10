package com.abocha.epamtelescope.data.network.services

import com.abocha.epamtelescope.data.network.models.requests.RiserClampsRequest
import io.reactivex.Completable
import retrofit2.http.Body
import retrofit2.http.POST

interface SurveyService {

    @POST("surveys/riser_clamps/")
    fun sendRiseClamp(@Body request: RiserClampsRequest): Completable

}

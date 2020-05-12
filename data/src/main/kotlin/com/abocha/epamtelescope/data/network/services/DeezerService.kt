package com.abocha.epamtelescope.data.network.services

import com.abocha.epamtelescope.data.network.models.requests.RefreshRequest
import com.abocha.epamtelescope.data.network.models.responses.TrackResponseData
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface DeezerService {

    @GET("/search/track/?q=eminem&index=0&limit=2&output=json")
    fun getSongs(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
        ): Single<TrackResponseData>

   /* @GET("campaigns/assignments/offline/my/")
    fun getAssignments(): Single<List<AssignmentsResponse>>

    @GET("dictionaries/?is_paginate=false")
    fun getDictionaries(): Single<DictionariesResponse>*/

    @POST("auth/logout/")
    fun logout(@Body refreshRequest: RefreshRequest): Completable
}

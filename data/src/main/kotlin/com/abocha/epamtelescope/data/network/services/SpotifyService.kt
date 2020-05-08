package com.abocha.epamtelescope.data.network.services

import com.abocha.epamtelescope.data.network.models.requests.RefreshRequest
import com.abocha.epamtelescope.data.network.models.responses.SongResponse
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SpotifyService {

    @GET("fs")
    fun getSongs(): Single<List<SongResponse>>

   /* @GET("campaigns/assignments/offline/my/")
    fun getAssignments(): Single<List<AssignmentsResponse>>

    @GET("dictionaries/?is_paginate=false")
    fun getDictionaries(): Single<DictionariesResponse>*/

    @POST("auth/logout/")
    fun logout(@Body refreshRequest: RefreshRequest): Completable
}

package com.abocha.epamtelescope.data.network.services

import com.abocha.epamtelescope.data.network.models.requests.RefreshRequest
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

/*    @POST("auth/login/")
    fun login(@Body loginRequest: LoginRequest): Single<LoginResponse>*/

    @POST("auth/refresh/")
    fun refresh(@Body refreshRequest: RefreshRequest): Call<ResponseBody>
}

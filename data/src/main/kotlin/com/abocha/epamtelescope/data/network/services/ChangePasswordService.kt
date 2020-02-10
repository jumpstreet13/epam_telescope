package com.abocha.epamtelescope.data.network.services

import com.abocha.epamtelescope.data.network.models.requests.ConfirmCodeRequest
import com.abocha.epamtelescope.data.network.models.requests.FinishChangePasswordRequest
import com.abocha.epamtelescope.data.network.models.responses.ChangePasswordTokenResponse
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface ChangePasswordService {

    @POST("users/account/change_password/request/")
    fun requestCode(): Completable

    @POST("users/account/change_password/confirm_code/")
    fun confirmCode(@Body confirmCodeRequest: ConfirmCodeRequest): Single<ChangePasswordTokenResponse>

    @POST("users/account/change_password/finish/")
    fun finish(@Body finishChangePasswordRequest: FinishChangePasswordRequest): Completable

}

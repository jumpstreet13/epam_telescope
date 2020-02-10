package com.abocha.epamtelescope.data.network.services

import com.abocha.epamtelescope.data.network.models.requests.ConfirmEmailRequest
import com.abocha.epamtelescope.data.network.models.requests.EmailRequest
import com.abocha.epamtelescope.data.network.models.requests.FinishRecoveryPasswordRequest
import com.abocha.epamtelescope.data.network.models.responses.RecoveryTokenResponse
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface RecoveryPasswordService {

    @POST("users/account/recover_password/request/")
    fun requestCode(@Body emailRequest: EmailRequest): Completable

    @POST("users/account/recover_password/confirm_code/")
    fun confirmCode(@Body confirmEmailRequest: ConfirmEmailRequest): Single<RecoveryTokenResponse>

    @POST("users/account/recover_password/finish/")
    fun finishPassword(@Body finishRecoveryPasswordRequest: FinishRecoveryPasswordRequest): Completable

}

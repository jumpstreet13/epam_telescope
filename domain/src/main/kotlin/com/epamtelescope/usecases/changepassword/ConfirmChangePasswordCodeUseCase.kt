package com.abocha.epamtelescope.usecases.changepassword

import com.abocha.epamtelescope.repository.ChangePasswordRepository
import io.reactivex.Single
import javax.inject.Inject

class ConfirmChangePasswordCodeUseCase @Inject constructor(
    private val changePasswordRepository: ChangePasswordRepository
) {

    fun execute(code: String): Single<String> =
        changePasswordRepository.confirmCode(code)
}

package com.abocha.epamtelescope.usecases.changepassword

import com.abocha.epamtelescope.repository.ChangePasswordRepository
import io.reactivex.Completable
import javax.inject.Inject

class SendChangePasswordCodeUseCase @Inject constructor(
    private val changePasswordRepository: ChangePasswordRepository
) {

    fun execute(): Completable =
        changePasswordRepository.requestCode()
}

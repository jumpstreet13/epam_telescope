package com.abocha.epamtelescope.usecases

import com.abocha.epamtelescope.repository.WorkRepository
import javax.inject.Inject

/**
 * Created by Oleg Sheliakin on 2020-01-21.
 * Contact me by email - olegsheliakin@gmail.com
 */
class StartRefreshDataUseCase @Inject constructor(
    private val workRepository: WorkRepository
) {

    fun execute() {
        workRepository.startPeriodicRefreshData()
    }
}

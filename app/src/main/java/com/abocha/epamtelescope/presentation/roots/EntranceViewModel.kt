package com.abocha.epamtelescope.presentation.roots

import androidx.lifecycle.ViewModel
import com.abocha.epamtelescope.R
import com.abocha.epamtelescope.common.errors.ErrorHandler
import com.abocha.epamtelescope.common.errors.HasErrors
import com.abocha.epamtelescope.common.navigation.ActionToOpenActivity
import com.abocha.epamtelescope.common.navigation.AppRouter
import com.abocha.epamtelescope.common.navigation.HasNavigation
import javax.inject.Inject

/**
 * @author Magomedov Abakar
 */
class EntranceViewModel @Inject constructor(
    private val router: AppRouter,
    private val errorHandler: ErrorHandler
) : ViewModel(), HasNavigation by router, HasErrors by errorHandler {

    private val openMainScreenCommand = ActionToOpenActivity(
        clearTask = true,
        destinationId = R.id.nav_main_screen
    )

    fun goToMainScreen() {
        router.withNavController(openMainScreenCommand)
    }
}
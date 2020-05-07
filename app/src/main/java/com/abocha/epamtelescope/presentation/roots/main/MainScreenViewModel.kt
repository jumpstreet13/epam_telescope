package com.abocha.epamtelescope.presentation.roots.main

import com.abocha.epamtelescope.common.errors.ErrorHandler
import com.abocha.epamtelescope.common.errors.HasErrors
import com.abocha.epamtelescope.common.mvi.MviViewModel
import com.abocha.epamtelescope.common.navigation.AppRouter
import com.abocha.epamtelescope.common.navigation.HasNavigation
import javax.inject.Inject

/**
 * @author Magomedov Abakar
 */
class MainScreenViewModel @Inject constructor(
    private val router: AppRouter,
    private val errorHandler: ErrorHandler
) : MviViewModel<Unit, Action>(), HasNavigation by router, HasErrors by errorHandler
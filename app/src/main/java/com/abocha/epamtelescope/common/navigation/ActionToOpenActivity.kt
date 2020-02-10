package com.abocha.epamtelescope.common.navigation

import android.content.Intent
import android.os.Bundle
import androidx.navigation.ActivityNavigatorExtras
import androidx.navigation.NavController
import androidx.navigation.NavOptions

/**
 * Created by Oleg Sheliakin on 2020-01-14.
 * Contact me by email - olegsheliakin@gmail.com
 */
class ActionToOpenActivity(
    private val destinationId: Int,
    private val clearTask: Boolean = false,
    private val args: Bundle? = null,
    private val navOptions: NavOptions? = null
) : NavigationCommand {
    override fun invoke(navController: NavController) {
        navController.navigate(
            destinationId, args, navOptions,
            ActivityNavigatorExtras(activityOptions = null, flags = getFlags())
        )
    }

    private fun getFlags() = if (clearTask) {
        Intent.FLAG_ACTIVITY_NEW_TASK.or(Intent.FLAG_ACTIVITY_CLEAR_TASK)
    } else {
        0
    }

}

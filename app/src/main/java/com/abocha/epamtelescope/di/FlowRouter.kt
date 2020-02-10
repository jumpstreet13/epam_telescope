package com.abocha.epamtelescope.di

import com.abocha.epamtelescope.common.navigation.AppRouter
import com.abocha.epamtelescope.common.navigation.HasNavigation
import com.abocha.epamtelescope.common.navigation.Router
import com.abocha.epamtelescope.common.navigation.RouterDelegate

/**
 * Created by Oleg Sheliakin on 2020-01-28.
 * Contact me by email - olegsheliakin@gmail.com
 */
class FlowRouter constructor(
    private val appRouter: AppRouter,
    private val delegate: RouterDelegate
) : Router by delegate, HasNavigation by delegate {

    override fun close() {
        withNavController {
            if (!it.popBackStack()) {
                appRouter.close()
            }
        }
    }
}

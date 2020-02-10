package com.abocha.epamtelescope.common.navigation

import androidx.lifecycle.LiveData

/**
 * Created by Oleg Sheliakin on 2019-12-24.
 * Contact me by email - olegsheliakin@gmail.com
 */
interface HasNavigation {
    val navigationEvents: LiveData<NavigationCommand>
}

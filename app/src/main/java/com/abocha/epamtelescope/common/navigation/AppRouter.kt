package com.abocha.epamtelescope.common.navigation

import com.abocha.epamtelescope.common.di.PerApplication
import javax.inject.Inject

/**
 * Created by Oleg Sheliakin on 2019-12-24.
 * Contact me by email - olegsheliakin@gmail.com
 */
@PerApplication
class AppRouter @Inject constructor(
    private val delegate: RouterDelegate
) : Router by delegate, HasNavigation by delegate

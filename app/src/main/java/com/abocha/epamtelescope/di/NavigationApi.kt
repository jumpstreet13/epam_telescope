package com.abocha.epamtelescope.di

import com.abocha.epamtelescope.common.navigation.Router

/**
 * @author Magomedov Abakar
 */
interface NavigationApi {
    fun router(): Router
}

interface NavigationApiProvider {
    val navigationApi: NavigationApi
}

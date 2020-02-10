package com.abocha.epamtelescope.di

import android.content.Context
import com.abocha.epamtelescope.common.Toaster
import com.abocha.epamtelescope.common.errors.ErrorHandler
import com.abocha.epamtelescope.common.navigation.AppRouter

/**
 * Created by Oleg Sheliakin on 2019-12-24.
 * Contact me by email - olegsheliakin@gmail.com
 */
interface CoreApi {
    fun errorMapper(): ErrorHandler
    fun toaster(): Toaster
    fun appRouter(): AppRouter
    fun applicationContext(): Context
}

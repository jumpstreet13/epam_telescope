package com.abocha.epamtelescope.di

import com.abocha.epamtelescope.common.Toaster
import com.abocha.epamtelescope.common.ToasterImpl
import com.abocha.epamtelescope.common.di.PerApplication
import com.abocha.epamtelescope.common.errors.ErrorHandler
import com.abocha.epamtelescope.common.errors.MainErrorHandler
import com.abocha.epamtelescope.common.navigation.AppRouter
import com.abocha.epamtelescope.common.navigation.Router
import dagger.Binds
import dagger.Module

/**
 * Created by Oleg Sheliakin on 2019-12-24.
 * Contact me by email - olegsheliakin@gmail.com
 */
@Module
interface CoreModule {

    @PerApplication
    @Binds
    fun bindErrorMapper(impl: MainErrorHandler): ErrorHandler

    @PerApplication
    @Binds
    fun bindToaster(impl: ToasterImpl): Toaster

    @PerApplication
    @Binds
    fun bindRouter(impl: AppRouter): Router
}

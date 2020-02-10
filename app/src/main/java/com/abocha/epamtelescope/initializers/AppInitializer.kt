package com.abocha.epamtelescope.initializers

import android.app.Application

/**
 * Created by Oleg Sheliakin on 04.12.2018.
 * Contact me by email - olegsheliakin@gmail.com
 */
interface AppInitializer {
    fun init(app: Application)
}

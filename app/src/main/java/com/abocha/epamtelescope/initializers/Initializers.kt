package com.abocha.epamtelescope.initializers

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.miguelbcr.ui.rx_paparazzo2.RxPaparazzo
import javax.inject.Inject

class RxPaparazzoInitializer @Inject constructor() : AppInitializer {
    companion object {
        private const val PATH_IMAGES = "Telescope/"
        private const val AUTHORITY = "com.abocha.epam_telescope.fileProvider"
    }

    override fun init(app: Application) {
        RxPaparazzo.register(app)
            .withFileProviderPath(PATH_IMAGES)
            .withFileProviderAuthority(AUTHORITY)
    }
}

class ThreeTenInitializer @Inject constructor() : AppInitializer {
    override fun init(app: Application) {
        AndroidThreeTen.init(app)
    }
}

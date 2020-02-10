package com.abocha.epamtelescope.initializers

import android.app.Application
import javax.inject.Inject

class TelescopeInitializer @Inject constructor(
    private val initializers: Set<@JvmSuppressWildcards AppInitializer>
) : AppInitializer {
    override fun init(app: Application) {
        initializers.forEach { it.init(app) }
    }
}

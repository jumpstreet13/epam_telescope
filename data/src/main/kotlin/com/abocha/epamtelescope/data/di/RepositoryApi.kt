package com.abocha.epamtelescope.data.di

import com.epamtelescope.repository.SongGateway

/**
 * Created by Oleg Sheliakin on 2019-12-23.
 * Contact me by email - olegsheliakin@gmail.com
 */
@Suppress("ComplexInterface", "TooManyFunctions")
interface RepositoryApi {
    fun songRepository(): SongGateway
}

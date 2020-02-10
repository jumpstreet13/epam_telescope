package com.abocha.epamtelescope.common.errors

import androidx.lifecycle.LiveData

/**
 * Created by Oleg Sheliakin on 2020-01-13.
 * Contact me by email - olegsheliakin@gmail.com
 */
interface HasErrors {

    val errorEvents: LiveData<String>
}

package com.abocha.epamtelescope.presentation.roots.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.abocha.epamtelescope.R
import com.abocha.epamtelescope.presentation.roots.di.MainApi
import com.abocha.epamtelescope.presentation.roots.di.MainApiProvider
import com.abocha.epamtelescope.presentation.roots.di.MainScreenComponent

/**
 * @author Magomedov Abakar
 */
class MainScreenActivity : AppCompatActivity(), MainApiProvider {

    override val mainApi: MainApi
        get() = mainScreenComponent

    private val mainScreenComponent: MainScreenComponent by lazy {
        MainScreenComponent(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)
    }
}
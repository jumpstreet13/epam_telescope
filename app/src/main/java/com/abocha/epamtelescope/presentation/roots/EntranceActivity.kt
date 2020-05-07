package com.abocha.epamtelescope.presentation.roots

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.abocha.epamtelescope.R
import com.abocha.epamtelescope.common.Toaster
import com.abocha.epamtelescope.presentation.roots.di.EntranceComponent
import javax.inject.Inject

class EntranceActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var toaster: Toaster

    private val viewModel: EntranceViewModel by viewModels { viewModelFactory }

    private val navController: NavController
        get() = findNavController(R.id.nav_host_fragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        EntranceComponent(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrance)

        viewModel.errorEvents.observe(this, Observer {
            toaster.show(this, it)
        })

        viewModel.navigationEvents.observe(this, Observer {
            it.invoke(navController)
        })

        viewModel.goToMainScreen()
    }
}

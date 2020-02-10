package com.abocha.epamtelescope.common.navigation

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.lifecycle.LiveData
import androidx.navigation.NavDirections
import com.abocha.epamtelescope.common.SingleLiveEvent
import javax.inject.Inject

interface Router {

    fun navigateTo(destinationId: Int)

    fun navigateTo(navDirections: NavDirections)

    fun close()

    fun withNavController(navCommand: NavigationCommand)
}

class RouterDelegate @Inject constructor() : Router, HasNavigation {
    private val navigationLiveData: SingleLiveEvent<NavigationCommand> by lazy { SingleLiveEvent<NavigationCommand>() }

    override val navigationEvents: LiveData<NavigationCommand> by lazy {
        navigationLiveData
    }

    override fun navigateTo(destinationId: Int) {
        withNavController {
            it.navigate(destinationId)
        }
    }

    override fun navigateTo(navDirections: NavDirections) {
        withNavController {
            it.navigate(navDirections)
        }
    }

    override fun close() {
        withNavController {
            it.popBackStack()
        }
    }

    override fun withNavController(navCommand: NavigationCommand) {
        if (!ArchTaskExecutor.getInstance().isMainThread) {
            navigationLiveData.postValue(navCommand)
        } else {
            navigationLiveData.value = navCommand
        }
    }
}

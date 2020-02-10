package com.abocha.epamtelescope.common.errors

import android.annotation.SuppressLint
import android.content.Context
import androidx.arch.core.executor.ArchTaskExecutor
import androidx.lifecycle.LiveData
import com.abocha.epamtelescope.R
import com.abocha.epamtelescope.common.SingleLiveEvent
import com.abocha.epamtelescope.common.navigation.ActionToOpenActivity
import com.abocha.epamtelescope.common.navigation.Router
import com.abocha.epamtelescope.exceptions.HttpException
import com.abocha.epamtelescope.exceptions.ServiceUnavailableException
import com.abocha.epamtelescope.exceptions.UserSessionExpiredException
import com.abocha.epamtelescope.pictures.PermissionsDeniedException
import com.abocha.epamtelescope.presentation.base.FeatureNotImplementedException
import com.abocha.epamtelescope.repository.AuthRepository
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber
import java.io.IOException
import java.io.InterruptedIOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

/**
 * Created by Oleg Sheliakin on 2019-12-24.
 * Contact me by email - olegsheliakin@gmail.com
 */
class MainErrorHandler @Inject constructor(
    private val context: Context,
    private val router: Router,
    private val authRepository: AuthRepository
) : ErrorHandler {

    private val errorEventLiveData: SingleLiveEvent<String> by lazy { SingleLiveEvent<String>() }

    private val openSignInScreenCommand = ActionToOpenActivity(
        clearTask = true,
        destinationId = 43/*R.id.nav_entrance*/
    )

    override val errorEvents: LiveData<String> by lazy { errorEventLiveData }

    @SuppressLint("CheckResult")
    override fun handleError(error: Throwable) {
        Timber.e(error)
        val errorText = map(error)
        if (!ArchTaskExecutor.getInstance().isMainThread) {
            errorEventLiveData.postValue(errorText)
        } else {
            errorEventLiveData.value = errorText
        }

        if (error is UserSessionExpiredException) {
            authRepository
                .logout()
                .onErrorComplete()
                .subscribeBy {
                    router.withNavController(openSignInScreenCommand)
                }
        }
    }

    @Suppress("ComplexMethod")
    private fun map(error: Throwable): String =
        when (error) {
            is HttpException -> error.message
            is ServiceUnavailableException -> context.getString(R.string.all_error_internal_server)
            is SocketTimeoutException -> context.getString(R.string.all_error_internet_connection)
            is IOException -> context.getString(R.string.all_error_internet_connection)
            is UnknownHostException -> context.getString(R.string.all_error_internet_connection)
            is ConnectException -> context.getString(R.string.all_error_internet_connection)
            is InterruptedIOException -> context.getString(R.string.all_error_internet_connection)
            is UserSessionExpiredException -> context.getString(R.string.all_error_session_expired)
            is FeatureNotImplementedException -> context.getString(R.string.all_error_not_implemented)
            is PermissionsDeniedException -> context.getString(R.string.please_give_permission)
            else -> context.getString(R.string.all_error_unexpected)
        }

}

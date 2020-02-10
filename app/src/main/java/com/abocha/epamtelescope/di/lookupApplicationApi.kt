package com.abocha.epamtelescope.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

/**
 * Created by Oleg Sheliakin on 2019-12-24.
 * Contact me by email - olegsheliakin@gmail.com
 */
fun Fragment.applicationApi(): ApplicationApi =
    this.lookup<ApplicationApiProvider>().applicationApi

fun FragmentActivity.applicationApi(): ApplicationApi =
    this.lookup<ApplicationApiProvider>().applicationApi

@Suppress("TooGenericExceptionThrown")
inline fun <reified T> Fragment.lookup(): T {
    var res: T? = null

    var parentFragment: Fragment? = this
    while (parentFragment != null) {
        if (parentFragment is T) {
            res = parentFragment
            break
        }

        parentFragment = parentFragment.parentFragment
    }

    if (res == null) {
        res = activity as? T
    }

    if (res == null) {
        res = activity?.application as? T
    }

    return res ?: throw IllegalArgumentException(
        String.format(
            "No ${T::class.java.simpleName} was found for %s",
            this.javaClass.canonicalName
        )
    )
}

@Suppress("TooGenericExceptionThrown")
inline fun <reified T> FragmentActivity.lookup(): T {
    val application = this.application
    if (application !is T) {
        throw RuntimeException(
            String.format(
                "%s does not implement %s",
                application.javaClass.canonicalName,
                T::class.java.canonicalName
            )
        )
    }

    return application
}

package com.abocha.epamtelescope.presentation.base

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

/**
 * @author Magomedov Abakar
 */
abstract class BaseFragment : Fragment {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    constructor() : super()

    constructor(layoutId: Int) : super(layoutId)

    override fun onAttach(context: Context) {
        inject()
        super.onAttach(context)
    }

    /**
     * Always use method in fragment to avoid multiple observers
     */
    fun <T> LiveData<T>.observeOnView(observer: (T) -> Unit) {
        this.observe(viewLifecycleOwner, Observer(observer::invoke))
    }

    fun <T> LiveData<T>.observeOnInstance(observer: (T) -> Unit) {
        this.observe(this@BaseFragment, Observer(observer::invoke))
    }

    protected abstract fun inject()

}
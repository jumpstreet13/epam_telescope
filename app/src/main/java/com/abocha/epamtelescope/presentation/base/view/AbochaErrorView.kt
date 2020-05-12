package com.abocha.epamtelescope.presentation.base.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.abocha.epamtelescope.R
import com.abocha.epamtelescope.presentation.base.rx.click
import com.abocha.epamtelescope.presentation.base.rx.disposableDelegate
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.abocha_error_view.view.*

/**
 * @author Magomedov Abakar
 */
class AbochaErrorView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var disposable: Disposable? by disposableDelegate()

    init {
        LayoutInflater.from(context).inflate(R.layout.abocha_error_view, this, true)
    }

    override fun onDetachedFromWindow() {
        disposable?.dispose()
        super.onDetachedFromWindow()
    }

    fun bindClick(click: (Unit) -> Unit) {
        textRetry.click()
            .subscribe(click)
            .also { disposable = it }
    }
}
package com.abocha.epamtelescope.common.views

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.core.content.withStyledAttributes
import androidx.core.view.isVisible
import com.abocha.epamtelescope.R
import kotlinx.android.synthetic.main.layout_loading_button.view.*

class LoadingButton : FrameLayout {

    constructor(context: Context) : super(context) {
        inflate(context, R.layout.layout_loading_button, this)
    }

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        inflate(context, R.layout.layout_loading_button, this).apply {
            context.withStyledAttributes(
                attrs,
                R.styleable.LoadingButton,
                0, 0
            ) {
                buttonProgress.text = this.getString(R.styleable.LoadingButton_buttonText)
            }
        }
    }

    fun showLoading(isLoading: Boolean) {
        buttonProgress.isVisible = !isLoading
        progress.isVisible = isLoading
    }

}

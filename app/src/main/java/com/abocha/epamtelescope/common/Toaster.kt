package com.abocha.epamtelescope.common

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.abocha.epamtelescope.R
import kotlinx.android.synthetic.main.layout_toast.view.*
import javax.inject.Inject

/**
 * Created by Oleg Sheliakin on 2019-12-24.
 * Contact me by email - olegsheliakin@gmail.com
 */
interface Toaster {
    fun show(
        context: Context,
        text: String,
        gravity: ToastGravity = ToastGravity.BOTTOM,
        type: ToastType = ToastType.ERROR
    )
}

enum class ToastGravity(val value: Int) {
    TOP(Gravity.TOP or Gravity.CENTER_HORIZONTAL),
    BOTTOM(Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL)
}

enum class ToastType(val colorRes: Int) {
    ERROR(R.color.color_white),
    MESSAGE(R.color.text_green)
}

class ToasterImpl @Inject constructor() : Toaster {

    private var currentToast: Toast? = null

    override fun show(
        context: Context,
        text: String,
        gravity: ToastGravity,
        type: ToastType
    ) {
        val toastView = createView(context, text, type)
        showInternal(context, gravity, toastView)
    }

    private fun showInternal(context: Context, gravity: ToastGravity, toastView: View) {
        currentToast?.cancel()

        currentToast = Toast(context).apply {
            view = toastView
            setGravity(gravity.value, 0, 0)
        }

        currentToast?.show()
    }

    @Suppress("InflateParams")
    private fun createView(
        context: Context, text: String, type: ToastType
    ): View =
        LayoutInflater.from(context).inflate(R.layout.layout_toast, null)
            .apply {
                tvErrorMessage.setTextColor(ContextCompat.getColor(context, type.colorRes))
                tvErrorMessage.text = text
            }

}

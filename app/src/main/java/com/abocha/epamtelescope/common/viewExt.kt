package com.abocha.epamtelescope.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Checkable
import android.widget.EditText
import android.widget.ScrollView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import com.abocha.epamtelescope.common.mvi.binder.BindAction
import com.google.android.material.textfield.TextInputLayout

/**
 * Created by Oleg Sheliakin on 2020-01-09.
 * Contact me by email - olegsheliakin@gmail.com
 */
fun EditText.bindTextAction(): BindAction<String?> = {
    if (this.textOrNull() != it) {
        this.setText(it)
    }
}

fun EditText.bindIntAction(): BindAction<Int?> = {
    if (this.intValue() != it) {
        this.setText(it?.toString())
    }
}

fun Checkable.bindAction(): BindAction<Boolean?> = {
    isChecked = it ?: false
}

fun EditText.textOrNull(): String? = text?.toString()

fun EditText.intValue(): Int? =
    text?.toString()?.toIntOrNull()

fun TextInputLayout.setError(@StringRes errorRes: Int? = null) {
    this.error = errorRes?.let { this.context.getString(it) }
}

fun TextInputLayout.resetErrorOnTyping() {
    editText?.doOnTextChanged { _, _, _, _ ->
        error = null
    }
}

fun ViewGroup.inflate(@LayoutRes layoutId: Int, attachToRoot: Boolean = false): View =
    LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)

fun ScrollView.disableAutoScroll() {
    descendantFocusability = ViewGroup.FOCUS_BEFORE_DESCENDANTS
    isFocusable = true
    isFocusableInTouchMode = true
    setOnTouchListener { v, _ ->
        v.requestFocusFromTouch()
        false
    }
}

inline fun TextView.onInputChanged(crossinline action: (text: String) -> Unit) {
    var isInitial = true

    addTextChangedListener(onTextChanged = { text, _, _, _ ->
        if (isInitial) {
            isInitial = false
        } else {
            action.invoke(text?.toString() ?: "")
        }
    })
}

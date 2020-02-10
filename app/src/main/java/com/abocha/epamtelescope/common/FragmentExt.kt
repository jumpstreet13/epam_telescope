package com.abocha.epamtelescope.common

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun Fragment.setTitle(title: String) {
    (activity as? AppCompatActivity)?.supportActionBar?.title = title
}

fun Fragment.setHomeIndicator(indicator: Int) =
    (activity as? AppCompatActivity)?.supportActionBar?.setHomeAsUpIndicator(indicator)

package com.wishbox.core.presentation.api.adapter

import android.os.Bundle

/**
 * @author Magomedov Abakar
 */

interface DelegateWithInstanceState {
    fun onSaveInstanceState(outState: Bundle?)
    fun onRestoreInstanceState(savedInstanceState: Bundle?)
}
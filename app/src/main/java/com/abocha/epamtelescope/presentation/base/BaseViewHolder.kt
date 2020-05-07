package com.abocha.epamtelescope.presentation.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer

/**
 * @author Magomedov Abakar
 */
abstract class BaseViewHolder<T>(override val containerView: View) :
    RecyclerView.ViewHolder(containerView), LayoutContainer {
    abstract fun bind(model: T)
}

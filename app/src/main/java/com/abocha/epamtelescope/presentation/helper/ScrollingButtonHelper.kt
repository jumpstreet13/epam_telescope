package com.abocha.epamtelescope.presentation.helper

import android.animation.Animator
import android.os.Bundle
import android.view.View
import android.view.animation.Interpolator
import androidx.interpolator.view.animation.FastOutLinearInInterpolator
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator
import androidx.recyclerview.widget.RecyclerView
import javax.inject.Inject

/**
 * @author Magomedov Abakar
 */

class ScrollingButtonHelper @Inject constructor() {

    companion object {
        private const val BUNDLE_CURRENT_STATE = "BUNDLE_CURRENT_STATE"

        private const val DURATION = 200L
    }

    private var currentState: State = State.SHOW

    fun withRecyclerView(recyclerView: RecyclerView, target: View) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                when {
                    dy > 0 && currentState == State.SHOW -> {
                        currentState = State.HIDE
                        target.triggerAnim()
                    }
                    (dy < 0 && currentState == State.HIDE) || (recyclerView.computeVerticalScrollRange() <= recyclerView.height) -> {
                        currentState = State.SHOW
                        target.triggerAnim()
                    }
                }
            }
        })
    }

    private fun View.triggerAnim() {
        animate()
            .scaleX(currentState.scale)
            .scaleY(currentState.scale)
            .alpha(currentState.alpha)
            .setDuration(DURATION)
            .setInterpolator(currentState.interpolator)
            .setListener(object : EmptyAnimatorListener {
                override fun onAnimationEnd(animation: Animator?) {
                    isClickable = currentState.clickable
                }
            })
            .start()
    }

    fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(BUNDLE_CURRENT_STATE, currentState.ordinal)
    }

    fun onViewStateRestored(savedInstanceState: Bundle?) {
        currentState = State.values()[savedInstanceState?.getInt(BUNDLE_CURRENT_STATE) ?: 0]
    }

    private enum class State(
        val scale: Float,
        val alpha: Float,
        val interpolator: Interpolator,
        val clickable: Boolean
    ) {
        SHOW(1f, 1f, LinearOutSlowInInterpolator(), true),
        HIDE(0f, 0f, FastOutLinearInInterpolator(), false)
    }
}
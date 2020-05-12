package com.abocha.epamtelescope.presentation.helper

import android.animation.Animator

/**
 * @author Magomedov Abakar
 */

interface EmptyAnimatorListener : Animator.AnimatorListener {
    override fun onAnimationRepeat(animation: Animator?) {}
    override fun onAnimationEnd(animation: Animator?) {}
    override fun onAnimationCancel(animation: Animator?) {}
    override fun onAnimationStart(animation: Animator?) {}
}
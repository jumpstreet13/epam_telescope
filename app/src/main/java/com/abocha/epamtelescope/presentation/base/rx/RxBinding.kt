package com.abocha.epamtelescope.presentation.base.rx

import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.widget.CompoundButton
import android.widget.Switch
import android.widget.TextView
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.view.longClicks
import com.jakewharton.rxbinding3.view.touches
import com.jakewharton.rxbinding3.widget.checkedChanges
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

/**
 * @author Magomedov Abakar
 */

fun View.click(): Observable<Unit> =
    clicks()
        .throttleFirst(300, TimeUnit.MILLISECONDS)
        .observeOn(AndroidSchedulers.mainThread())

fun CompoundButton.checkedChanges(): Observable<Boolean> =
    checkedChanges().throttleFirst(300, TimeUnit.MILLISECONDS)
        .observeOn(AndroidSchedulers.mainThread())

fun Switch.touches(action: (motionEvent: MotionEvent) -> Boolean = { true }):
    Observable<Unit> = touches {
    action.invoke(it)
}
    .map { Unit }

fun View.longClick(handled: () -> Boolean = { true }): Observable<Unit> =
    longClicks(handled)

fun TextView.simpleTextChanges(
    delay: Long = 0,
    unit: TimeUnit = TimeUnit.MILLISECONDS
): Observable<String> =
    textChanges()
        .map { it.toString().trim() }

fun TextView.textChanges(
    delay: Long = 0,
    unit: TimeUnit = TimeUnit.MILLISECONDS
): Observable<String> =
    textChanges()
        .skipInitialValue()
        .let { if (delay != 0L) it.debounce(delay, unit).observeOn(AndroidSchedulers.mainThread()) else it }
        .map { it.toString().trim() }

//disallow copy and paste action for TextView
fun TextView.disableCopyAndPaste() {
    customSelectionActionModeCallback = object : ActionMode.Callback {

        override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean {
            return false
        }

        override fun onDestroyActionMode(mode: ActionMode) {}

        override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
            return false
        }

        override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean {
            return false
        }
    }
}

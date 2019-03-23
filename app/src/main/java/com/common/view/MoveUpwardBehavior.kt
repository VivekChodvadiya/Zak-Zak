package com.common.view

import android.content.Context
import android.util.AttributeSet
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.view.View


/**
 * Created by Softfinite (Viv'Ek Chodvadiya) on 13-Jun-18.
 */

class MoveUpwardBehavior : CoordinatorLayout.Behavior<View> {

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    override fun onDependentViewChanged(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
        val translationY = Math.min(0F, dependency.getTranslationY() - dependency.getHeight())
        child.setTranslationY(translationY)
        return true
    }

    override fun layoutDependsOn(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
        return dependency is Snackbar.SnackbarLayout
    }

    override fun onDependentViewRemoved(parent: CoordinatorLayout, child: View, dependency: View) {
        super.onDependentViewRemoved(parent, child, dependency)
        child.setTranslationY(0F)
    }
}
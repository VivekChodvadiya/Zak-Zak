package com.common.view

import android.content.Context
import android.util.AttributeSet
import com.softfinite.utils.Utils

/**
 * Created by Softfinite (Viv'Ek Chodvadiya) on 13-Jun-18.
 */
class CBButtonViewM : com.rey.material.widget.Button {

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context) : super(context) {
        init()
    }

    fun init() {
        if (!isInEditMode) {
            try {
                //                if (!Locale.getDefault().toString().startsWith("en"))
                typeface = Utils.getBold(context)
            } catch (e: Exception) {
            }

        }
    }
}
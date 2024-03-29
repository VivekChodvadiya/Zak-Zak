package com.common.view

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView

/**
 * Created by Softfinite (Viv'Ek Chodvadiya) on 13-Jun-18.
 */
class ImageViewSquare : ImageView {

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {

    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context) : super(context) {}

    public override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }
}
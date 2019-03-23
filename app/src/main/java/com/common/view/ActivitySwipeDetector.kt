package com.common.view

import android.view.MotionEvent
import android.widget.Toast
import android.app.Activity
import android.view.View
import com.softfinite.utils.Debug
import java.util.*

/**
 * Created by Softfinite (Viv'Ek Chodvadiya) on 13-Jun-18.
 */

class ActivitySwipeDetector(private val activity: Activity) : View.OnTouchListener {
    private var downX: Float = 0.toFloat()
    private var downY: Float = 0.toFloat()
    private var upX: Float = 0.toFloat()
    private var upY: Float = 0.toFloat()

    private lateinit var mEventListener: EventListener

    fun onRightToLeftSwipe() {
        if (mEventListener != null) {
            mEventListener.onLeftSwiped()
        }
        Debug.i(logTag, "RightToLeftSwipe!")
        //activity.doSomething();
    }

    fun onLeftToRightSwipe() {
        if (mEventListener != null) {
            mEventListener.onRightSwiped()
        }
        Debug.i(logTag, "LeftToRightSwipe!")
        //activity.doSomething();
    }

    fun onTopToBottomSwipe() {
        Debug.i(logTag, "onTopToBottomSwipe!")
        //Toast.makeText(activity, "onTopToBottomSwipe", Toast.LENGTH_SHORT).show();
        //activity.doSomething();
    }

    fun onBottomToTopSwipe() {
        Debug.i(logTag, "onBottomToTopSwipe!")
        //Toast.makeText(activity, "onBottomToTopSwipe", Toast.LENGTH_SHORT).show();
        //activity.doSomething();
    }

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                downX = event.x
                downY = event.y
                return true
            }
            MotionEvent.ACTION_UP -> {
                upX = event.x
                upY = event.y

                val deltaX = downX - upX
                val deltaY = downY - upY

                // swipe horizontal?
                if (Math.abs(deltaX) > MIN_DISTANCE) {
                    // left or right
                    if (deltaX < 0) {
                        this.onLeftToRightSwipe()
                        return true
                    }
                    if (deltaX > 0) {
                        this.onRightToLeftSwipe()
                        return true
                    }
                } else {
                    Debug.i(logTag, "Swipe was only " + Math.abs(deltaX) + " long, need at least " + MIN_DISTANCE)
                    return false // We don't consume the event
                }

                // swipe vertical?
                if (Math.abs(deltaY) > MIN_DISTANCE) {
                    // top or down
                    if (deltaY < 0) {
                        this.onTopToBottomSwipe()
                        return true
                    }
                    if (deltaY > 0) {
                        this.onBottomToTopSwipe()
                        return true
                    }
                } else {
                    Debug.i(logTag, "Swipe was only " + Math.abs(deltaX) + " long, need at least " + MIN_DISTANCE)
                    return false // We don't consume the event
                }

                return true
            }
        }
        return false
    }

    companion object {
        internal val logTag = "ActivitySwipeDetector"
        internal val MIN_DISTANCE = 100
    }

    fun setEventListener(eventListener: EventListener) {
        mEventListener = eventListener
    }

    interface EventListener {
        fun onLeftSwiped()
        fun onRightSwiped()
    }

}
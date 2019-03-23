package com.softfinite.utils

import android.os.Handler

/**
 * Created by Softfinite (Viv'Ek Chodvadiya) on 13-Jun-18.
 */

internal object ExitStrategy {

    private var isAbletoExit = false
    private val h = Handler()

    fun canExit(): Boolean {
        return isAbletoExit
    }

    fun startExitDelay(delayMillis: Long) {
        isAbletoExit = true
        h.postDelayed(runnable, delayMillis)
    }

    internal var runnable: Runnable = Runnable { isAbletoExit = false }

    fun shutDown() {
        isAbletoExit = false
        h.removeCallbacks(runnable)
    }

}
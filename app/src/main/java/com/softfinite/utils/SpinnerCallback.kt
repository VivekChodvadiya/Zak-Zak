package com.softfinite.utils

import com.softfinite.objects.Spinner
import java.util.*

/**
 * Created by Softfinite (Viv'Ek Chodvadiya) on 14-Jun-18.
 */

interface SpinnerCallback {
    abstract fun onDone(list: ArrayList<Spinner>)
}
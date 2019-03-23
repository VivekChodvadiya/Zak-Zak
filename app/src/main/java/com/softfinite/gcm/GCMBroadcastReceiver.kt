package com.softfinite.gcm

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.support.v4.content.WakefulBroadcastReceiver


/**
 * Created by Softfinite (Viv'Ek Chodvadiya) on 14-Jun-18.
 */
class GCMBroadcastReceiver : WakefulBroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // Explicitly specify that GcmIntentService will handle the intent.
        val comp = ComponentName(context.packageName,
                GCMIntentService::class.java.getName())
        // Start the service, keeping the device awake while it is launching.
        WakefulBroadcastReceiver.startWakefulService(context, intent.setComponent(comp))
        resultCode = Activity.RESULT_OK
    }

}
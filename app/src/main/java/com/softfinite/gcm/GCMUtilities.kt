package com.softfinite.gcm

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.AsyncTask
import com.google.android.gms.gcm.GoogleCloudMessaging
import com.softfinite.utils.Debug
import java.io.IOException


/**
 * Created by Softfinite (Viv'Ek Chodvadiya) on 14-Jun-18.
 */


class GCMUtilities(internal var activity: Activity) {

    /**
     * Gets the current registration ID for application on GCM service.
     *
     *
     * If result is empty, the app needs to register.
     *
     * @return registration ID, or empty string if there is no existing
     * registration ID.
     */
    // Check if app was updated; if so, it must clear the registration ID
    // since the existing regID is not guaranteed to work with the new
    // app version.
    val registrationId: String
        get() {
            val prefs = gcmPreferences
            val registrationId = prefs.getString(WINAPP_REG_ID, "")
            if (registrationId!!.isEmpty()) {
                return ""
            }
            val registeredVersion = prefs.getInt(WINAPP_APP_VERSION,
                    Integer.MIN_VALUE)
            val currentVersion = appVersion
            return if (registeredVersion != currentVersion) {
                ""
            } else registrationId
        }

    /**
     * @return Application's `SharedPreferences`.
     */
    private// This sample app persists the registration ID in shared preferences,
    // but
    // how you store the regID in your app is up to you.
    val gcmPreferences: SharedPreferences
        get() = activity.getSharedPreferences(REG_PREF, Context.MODE_PRIVATE)

    private val appVersion: Int
        get() {
            try {
                val packageInfo = activity.packageManager
                        .getPackageInfo(activity.packageName, 0)
                return packageInfo.versionCode
            } catch (e: PackageManager.NameNotFoundException) {
                throw RuntimeException("Could not get package name: $e")
            }

        }

    internal var onUpdatedAddDevice: OnUpdatedAddDeviceListener? = null

    fun initGCM() {
        val regId = registrationId
        if (regId == null || regId.isEmpty()) {
            RegisterInBackground().execute()
        }
    }

    private fun storeRegistrationId(regId: String?) {
        val prefs = gcmPreferences
        val appVersion = appVersion
        val editor = prefs.edit()
        editor.putString(WINAPP_REG_ID, regId)
        editor.putInt(WINAPP_APP_VERSION, appVersion)
        editor.apply()
    }

    private fun clearRegistrationId() {
        val prefs = gcmPreferences
        val editor = prefs.edit()
        editor.clear()
        editor.apply()
    }

    interface OnUpdatedAddDeviceListener {
        fun onComplete()
    }

    inner class RegisterInBackground : AsyncTask<Any, Any, Any>() {
        internal var msg = ""

        override fun doInBackground(vararg params: Any): Any {
            try {
                var gcm: GoogleCloudMessaging? = null
                val regid: String?

                if (gcm == null) {
                    gcm = GoogleCloudMessaging.getInstance(activity)
                }

                regid = gcm!!.register(SENDER_ID)
                msg = "Device registered, registration ID=" + regid!!
                Debug.e("", msg)


                // For this demo: we don't need to send it because the device
                // will send upstream messages to a server that echo back the
                // message using the 'from' address in the message.

                // Persist the regID - no need to register again.
                storeRegistrationId(regid)


                // You should send the registration ID to your server over HTTP,
                // so it can use GCM/HTTP or CCS to send messages to your app.
                // The request to your server should be authenticated if your
                // app
                // is using accounts.
                if (regid != null && !regid.isEmpty()) {
                    activity.runOnUiThread { }
                }

            } catch (ex: IOException) {
                msg = "Error :" + ex.message
                // If there is an error, don't just keep trying to register.
                // Require the user to click a button again, or perform
                // exponential back-off.
            }

            return msg
        }

    }

    fun unRegister() {
        try {

            var gcm: GoogleCloudMessaging? = null

            if (gcm == null) {
                gcm = GoogleCloudMessaging.getInstance(activity)
            }

            gcm!!.unregister()

            clearRegistrationId()

        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    companion object {

        /**
         * Google API project id registered to use GCM.
         */
        val SENDER_ID = "437806439696"
        val REG_PREF = "reg_pref"
        val WINAPP_REG_ID = "winapp_reg_id"
        val WINAPP_APP_VERSION = "winapp_app_version"
    }
}
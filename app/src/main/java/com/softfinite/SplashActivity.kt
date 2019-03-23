package com.softfinite

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.view.View
import com.afollestad.materialdialogs.MaterialDialog
import com.softfinite.utils.Constant
import com.softfinite.utils.Debug
import com.softfinite.utils.Utils
import com.nostra13.universalimageloader.core.ImageLoader
import kotlinx.android.synthetic.main.activity_splash.*

/**
 * Created by Softfinite (Viv'Ek Chodvadiya) on 13-Jun-18.
 */

class SplashActivity : BaseActivity() {

    internal var TAG = "SplashActivity"
    internal var handler = Handler()
    internal lateinit var imageLoader: ImageLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        if (Utils.isInternetConnected(getActivity())) {
//            GCMUtilities(getActivity()).initGCM()

            //            getUpdateVersion();
            //            startApplication(1000);
//            if (Utils.isLocationProviderEnabled(getActivity())) {
            startApplication(1000)
//            } else {
//                handler.post(postLocationDialog)
//            }
        } else {
            startApplication(1000)
        }
    }

    private fun startApplication(sleepTime: Long) {
        handler.postDelayed(startApp, sleepTime)
    }

    internal var startApp: Runnable = object : Runnable {
        override fun run() {
            handler.removeCallbacks(this)
            Debug.e(TAG, "startApp")
//            if (!Utils.isUserLoggedIn(getActivity())) {
//                val i = Intent(getActivity(), LoginActivity::class.java)
//                startActivity(i)
//                finish()
//            } else {
//                if (Utils.getPref(getActivity(), Constant.IS_EMAIL_CONFIRM, false)) {
            val changePage = Intent(getActivity(), MainActivity::class.java)
            startActivity(changePage)
            finish()
//                } else {
//                    val i = Intent(getActivity(), MainActivity::class.java)
//                    startActivity(i)
//                    finish()
//                }
//            }
        }
    }

    internal var count = 30
    internal var mPostInternetConDialog: Runnable = Runnable {
        val builder = MaterialDialog.Builder(getActivity())
                .title(getActivity().resources.getString(R.string.connection_title))
                .content(getActivity().resources.getString(R.string.connection_not_available))
                .positiveText(getActivity().resources.getString(R.string.btn_enable))
                .negativeText(getActivity().resources.getString(R.string.btn_cancel))
                .onPositive { materialDialog, dialogAction ->
                    try {
                        val intent = Intent(Settings.ACTION_WIFI_SETTINGS)
                        startActivity(intent)
                        finish()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }.onNegative { materialDialog, dialogAction -> finish() }
        val dialog = builder.build()
        dialog.show()
    }

    internal var postLocationDialog: Runnable = Runnable {
        val builder = MaterialDialog.Builder(getActivity())
                .title(getActivity().resources.getString(R.string.location_title))
                .content(getActivity().resources.getString(R.string.location_msg))
                .positiveText(getActivity().resources.getString(R.string.btn_settings))
                .negativeText(getActivity().resources.getString(R.string.btn_cancel))
                .onPositive { materialDialog, dialogAction ->
                    try {
                        val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                        startActivity(intent)
                        finish()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }.onNegative { materialDialog, dialogAction -> }

        val dialog = builder.build()
        dialog.show()
    }

    internal var checkConnection: Runnable = object : Runnable {
        override fun run() {
            Debug.e(TAG, "checkConnection")
            if (Utils.isInternetConnected(getActivity())) {
                splashMsg.setText(getString(R.string.connected))
                handler.removeCallbacks(this)
                if (Utils.isInternetConnected(getActivity())) {
                    startApplication(1000)
                } else {
                    handler.post(mPostInternetConDialog)
                }
            } else {
                if (count != 0) {
                    splashMsg.setText(String.format(
                            getString(R.string.retrying), "" + count--))
                    handler.postDelayed(this, 1000)
                } else {
                    splashMsg.setText(R.string.finishing)
                    finish()
                }
            }
        }
    }

    fun versionUpgradeDialog() {
        val builder = MaterialDialog.Builder(getActivity())
                .title("Update")
                .content(R.string.update_text)
                .positiveText("Update")
                .negativeText(R.string.btn_cancel)
                .onPositive(MaterialDialog.SingleButtonCallback { materialDialog, dialogAction ->
                    try {
                        val appPackageName = packageName
                        try {
                            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$appPackageName")))
                        } catch (anfe: android.content.ActivityNotFoundException) {
                            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=$appPackageName")))
                        }
                        finish()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }).onNegative(MaterialDialog.SingleButtonCallback { materialDialog, dialogAction -> finish() })
        val dialog = builder.build()
        dialog.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constant.REQ_CODE_SETTING) {
            splashMsg.setVisibility(View.VISIBLE)
            handler.post(checkConnection)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        try {
            handler.removeCallbacks(startApp)
            handler.removeCallbacks(checkConnection)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}
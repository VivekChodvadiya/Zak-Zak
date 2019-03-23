package com.softfinite

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.ImageView
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem
import com.softfinite.utils.Constant
import com.softfinite.utils.RetrofitProgressDialog
import com.softfinite.utils.Utils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.topbar.*
import kotlinx.android.synthetic.main.user_nav_header_main.view.*

/**
 * Created by Softfinite (Viv'Ek Chodvadiya) on 11-Jun-18.
 */

open class BaseActivity : AppCompatActivity() {

    internal var ad: RetrofitProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)

        toast = Toast.makeText(getActivity(), "", Toast.LENGTH_LONG)

        val intentFilter = IntentFilter()
        intentFilter.addAction(Constant.FINISH_ACTIVITY)

        commonReciever = MyEventServiceReciever()
        LocalBroadcastManager.getInstance(this).registerReceiver(
                commonReciever, intentFilter)
    }

    fun setTitleText(text: String) {
        try {
            tvTitleText.text = text
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    internal lateinit var commonReciever: MyEventServiceReciever

    internal inner class MyEventServiceReciever : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            try {
                if (intent.action!!.equals(
                                Constant.FINISH_ACTIVITY, ignoreCase = true)) {
                    finish()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getActivity(): BaseActivity {
        return this
    }

    fun showDialog(msg: String) {
        try {
            if (ad != null && ad!!.isShowing) {
                return
            }
            ad = RetrofitProgressDialog.getInstant(getActivity())
            ad!!.show(msg)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun dismissDialog() {
        try {
            if (ad != null) {
                ad!!.dismiss()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    internal lateinit var toast: Toast

    fun showToast(text: String, duration: Int) {
        runOnUiThread {
            toast.setText(text)
            toast.duration = duration
            toast.show()
        }
    }

    fun showToast(text: String) {
        runOnUiThread {
            toast.setText(text)
            toast.duration = Toast.LENGTH_SHORT
            toast.show()
        }
    }

    fun finishActivity() {
        if (getActivity() is MainActivity) {

        } else {
            getActivity().finish()
        }
    }

    fun initBottomNavigationBar() {
        val bottomNavigation = findViewById<View>(R.id.bottom_navigation) as AHBottomNavigation

        val item1 = AHBottomNavigationItem("Home", R.drawable.ic_home_black_24dp)
        val item2 = AHBottomNavigationItem("Ranking", R.drawable.ic_search_black_24dp)
        val item3 = AHBottomNavigationItem("Messages", R.drawable.ic_message_black_24dp)
        val item4 = AHBottomNavigationItem("Account", R.drawable.ic_account_circle_black_24dp)


        bottomNavigation.addItem(item1)
        bottomNavigation.addItem(item2)
        bottomNavigation.addItem(item3)
        bottomNavigation.addItem(item4)

        bottomNavigation.accentColor = resources.getColor(R.color.primary)
        bottomNavigation.defaultBackgroundColor = resources.getColor(R.color.white)
        bottomNavigation.inactiveColor = resources.getColor(R.color.black)
        bottomNavigation.titleState = AHBottomNavigation.TitleState.ALWAYS_SHOW
        bottomNavigation.isBehaviorTranslationEnabled = true


        //        try {
        ////            if (Integer.valueOf(Utils.getPref(getActivity(), Constant.CART_COUNT, "")) > 0) {
        ////                bottomNavigation.setNotification("" + Utils.getPref(getActivity(), Constant.CART_COUNT, ""), 2);
        ////            } else {
        ////                bottomNavigation.setNotification("", 2);
        ////            }
        //        } catch (Exception e) {
        //            bottomNavigation.setNotification("", 2);
        //        }

//        if (getActivity() is MainActivity) {
//            bottomNavigation.currentItem = 0
//        } else if (getActivity() is MainActivity) {
//            bottomNavigation.currentItem = 1
//        } else if (getActivity() is MainActivity) {
//            bottomNavigation.currentItem = 2
//        } else if (getActivity() is MainActivity) {
//            bottomNavigation.currentItem = 3
//        } else if (getActivity() is MainActivity) {
//            bottomNavigation.currentItem = 4
//        }


        bottomNavigation.setOnTabSelectedListener { position, wasSelected ->
            if (position == 0) {
                (getActivity() as MainActivity).pager.setCurrentItem(0, false)
//                (getActivity() as MainActivity).setTitleText("Home")
            } else if (position == 1) {
                (getActivity() as MainActivity).pager.setCurrentItem(1, false)
//                (getActivity() as MainActivity).setTitleText("Search")
            } else if (position == 2) {
                (getActivity() as MainActivity).pager.setCurrentItem(2, false)
//                (getActivity() as MainActivity).setTitleText("Moments")
            } else if (position == 3) {
                (getActivity() as MainActivity).pager.setCurrentItem(3, false)
//                (getActivity() as MainActivity).setTitleText("Cart")
            }
            true
        }
    }

    override fun onResume() {
        super.onResume()

        val bottomNavigation = findViewById<AHBottomNavigation>(R.id.bottom_navigation) as? AHBottomNavigation

        if (bottomNavigation != null) {
            if (getActivity() is MainActivity) {
                bottomNavigation.currentItem = 0
            } else if (getActivity() is MainActivity) {
                bottomNavigation.currentItem = 1
            } else if (getActivity() is MainActivity) {
                bottomNavigation.currentItem = 2
            } else if (getActivity() is MainActivity) {
                bottomNavigation.currentItem = 3
            }
        }
    }

    fun confirmLogout() {
        val builder = MaterialDialog.Builder(getActivity())
                .title(getActivity().resources.getString(R.string.logout_title))
                .content(getActivity().resources.getString(R.string.logout_msg))
                .positiveText(getActivity().resources.getString(R.string.btn_yes))
                .negativeText(getActivity().resources.getString(R.string.btn_no))
                .negativeColor(resources.getColor(R.color.primary))
                .positiveColor(resources.getColor(R.color.primary))
                .onPositive { materialDialog, dialogAction ->
                    //                        showToast(getActivity().getString(R.string.logout), Toast.LENGTH_SHORT);

                    Utils.clearLoginCredetials(getActivity())

                    LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(Intent(Constant.FINISH_ACTIVITY))

//                    val intent = Intent(getActivity(), LoginActivity::class.java)
//                    startActivity(intent)
                }.onNegative { materialDialog, dialogAction -> }

        val dialog = builder.build()
        dialog.show()
    }

    fun initBack(b: Boolean) {
        val imgBack = findViewById<View>(R.id.imgBack) as ImageView
        if (b) {
            imgBack.visibility = View.VISIBLE
            imgBack.setOnClickListener { finish() }
        } else {
            imgBack.visibility = View.GONE
        }
    }

    override fun onBackPressed() {
        try {
//            if (result.isDrawerOpen) {
//                result.closeDrawer()
//            } else {
            super.onBackPressed()
//            }
        } catch (e: Exception) {
            super.onBackPressed()
        }
    }

}
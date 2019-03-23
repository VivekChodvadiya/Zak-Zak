package com.softfinite.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.softfinite.R


/**
 * Created by Softfinite (Viv'Ek Chodvadiya) on 13-Jun-18.
 */
class AccountFragment : BaseFragment() {

    internal var type: Int = 0

    companion object {
        internal val LAYOUT_ID = "layoutid"
        fun newInstance(layoutId: Int, type: Int): AccountFragment {
            val pane = AccountFragment()
            val args = Bundle()
            args.putInt(LAYOUT_ID, layoutId)
            args.putInt("type", type)
            pane.arguments = args
            return pane
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_account_fragment, container, false)
    }

    private fun init() {
//        setTitleText("Account")


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        type = arguments!!.getInt("type", 0)
        init()
    }
}
package com.softfinite.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.softfinite.R
import com.softfinite.adapter.HomeAdapter
import kotlinx.android.synthetic.main.activity_home_fragment.*

/**
 * Created by Softfinite (Viv'Ek Chodvadiya) on 13-Jun-18.
 */

class FollowerFragment : BaseFragment() {

    internal var type: Int = 0
    internal lateinit var homeAdapter: HomeAdapter
    internal lateinit var mLayoutManager: RecyclerView.LayoutManager

    companion object {
        internal val LAYOUT_ID = "layoutid"
        fun newInstance(layoutId: Int, type: Int): FollowerFragment {
            val pane = FollowerFragment()
            val args = Bundle()
            args.putInt(LAYOUT_ID, layoutId)
            args.putInt("type", type)
            pane.arguments = args
            return pane
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_follower_fragment, container, false)
    }

    private fun init() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        type = arguments!!.getInt("type", 0)
        init()
    }
}
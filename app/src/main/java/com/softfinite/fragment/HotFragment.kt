package com.softfinite.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.softfinite.MainActivity
import com.softfinite.R
import com.softfinite.UserDetailActivity
import com.softfinite.adapter.UserAdapter
import com.softfinite.adapter.TabAdapter
import kotlinx.android.synthetic.main.activity_hot_fragment.*

/**
 * Created by Softfinite (Viv'Ek Chodvadiya) on 13-Jun-18.
 */

class HotFragment : BaseFragment() {

    internal var type: Int = 0

    lateinit var userAdapter: UserAdapter
    internal lateinit var mLayoutManager: RecyclerView.LayoutManager

    companion object {
        internal val LAYOUT_ID = "layoutid"
        fun newInstance(layoutId: Int, type: Int): HotFragment {
            val pane = HotFragment()
            val args = Bundle()
            args.putInt(LAYOUT_ID, layoutId)
            args.putInt("type", type)
            pane.arguments = args
            return pane
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_hot_fragment, container, false)
    }

    private fun init() {

        mLayoutManager = GridLayoutManager(getActivity(), 2)
        if (rvUser != null) rvUser!!.layoutManager = mLayoutManager
        userAdapter = UserAdapter(this.getActivity()!!)
        if (rvUser != null) rvUser!!.adapter = userAdapter

        userAdapter.setEventListener(object : UserAdapter.EventListener {
            override fun onItemViewClicked(position: Int) {
                val i = Intent(getActivity(), UserDetailActivity::class.java)
                startActivity(i)
            }
        })

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }
}
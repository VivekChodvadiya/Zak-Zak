package com.softfinite.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.softfinite.R
import com.softfinite.adapter.HomeAdapter
import com.softfinite.adapter.TabAdapter
import kotlinx.android.synthetic.main.activity_home_fragment.*

/**
 * Created by Softfinite (Viv'Ek Chodvadiya) on 13-Jun-18.
 */

class HomeFragment : BaseFragment() {

    lateinit var pagerAdapter: MainCatagoryAdapter

    lateinit var tabAdapter: TabAdapter
    internal lateinit var mLayoutManager: RecyclerView.LayoutManager

    internal var type: Int = 0

    companion object {
        internal val LAYOUT_ID = "layoutid"
        fun newInstance(layoutId: Int, type: Int): HomeFragment {
            val pane = HomeFragment()
            val args = Bundle()
            args.putInt(LAYOUT_ID, layoutId)
            args.putInt("type", type)
            pane.arguments = args
            return pane
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_home_fragment, container, false)
    }

    private fun init() {

        pagerAdapter = MainCatagoryAdapter(childFragmentManager)
        pager.setOffscreenPageLimit(pagerAdapter.count)
        pager.setAdapter(pagerAdapter)
        tab_layout.setupWithViewPager(pager)

        mLayoutManager = LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false)
        if (rvTab != null) rvTab!!.layoutManager = mLayoutManager
        tabAdapter = TabAdapter(this.getActivity()!!)
        if (rvTab != null) rvTab!!.adapter = tabAdapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        type = arguments!!.getInt("type", 0)
        init()
    }

    inner class MainCatagoryAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
        internal var tabTitles = arrayOf("Hot", "New", "Followed")
        override fun getItem(position: Int): Fragment {
            var f = Fragment()
            when (position) {
                0 -> f = HotFragment()
                1 -> f = NewFragment()
                2 -> f = FollowerFragment()
            }
            return f
        }

        override fun getCount(): Int {
            return 3
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return tabTitles[position]
        }
    }
}
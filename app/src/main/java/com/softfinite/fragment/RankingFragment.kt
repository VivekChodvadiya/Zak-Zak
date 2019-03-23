package com.softfinite.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.softfinite.R
import com.softfinite.utils.Utils
import kotlinx.android.synthetic.main.activity_ranking_fragment.*
import okhttp3.internal.Util

/**
 * Created by Softfinite (Viv'Ek Chodvadiya) on 13-Jun-18.
 */

class RankingFragment : BaseFragment() {

    internal var type: Int = 0
    lateinit var pagerAdapter: RankingAdapter

    companion object {
        internal val LAYOUT_ID = "layoutid"
        fun newInstance(layoutId: Int, type: Int): RankingFragment {
            val pane = RankingFragment()
            val args = Bundle()
            args.putInt(LAYOUT_ID, layoutId)
            args.putInt("type", type)
            pane.arguments = args
            return pane
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_ranking_fragment, container, false)
    }

    private fun init() {
//        setTitleText("Rankings")


        pagerAdapter = RankingAdapter(childFragmentManager)
        vpRanking.setOffscreenPageLimit(pagerAdapter.count)
        vpRanking.setAdapter(pagerAdapter)
        tlRanking.setupWithViewPager(vpRanking)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        type = arguments!!.getInt("type", 0)
        init()
    }


    inner class RankingAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
        internal var tabTitles = arrayOf("Charming", "Top Giver")
        override fun getItem(position: Int): Fragment {
            var f = Fragment()
            when (position) {
                0 -> f = CharmingFragment()
                1 -> f = TopGiverFragment()
            }
            return f
        }

        override fun getCount(): Int {
            return 2
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return tabTitles[position]
        }
    }
}
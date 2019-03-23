package com.softfinite

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.widget.Toast
import com.softfinite.fragment.*
import com.softfinite.utils.ExitStrategy
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by Softfinite (Viv'Ek Chodvadiya) on 13-Jun-18.
 */

class MainActivity : BaseActivity() {

    lateinit var pagerAdapter: HomePagePagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initBottomNavigationBar()
        init()
    }

    private fun init() {
//        setTitleText("Home")

        pagerAdapter = HomePagePagerAdapter(supportFragmentManager)
        pager.setOffscreenPageLimit(pagerAdapter.count)
        pager.setAdapter(pagerAdapter)
    }

    inner class HomePagePagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        //        private val tabTitles = arrayOf(getString(R.string.restaurant), getString(R.string.popular_menu))
        override fun getItem(position: Int): Fragment {
            var f = Fragment()
            when (position) {
                0 -> f = HomeFragment()
                1 -> f = RankingFragment()
                2 -> f = MessagesFragment()
                3 -> f = AccountFragment()
            }
            return f
        }

        override fun getCount(): Int {
            return 4
        }
//        override fun getPageTitle(position: Int): CharSequence? {
//            return tabTitles[position]
//        }
    }

    override fun onBackPressed() {
        try {
            if (pager.currentItem == 0) {
                if (ExitStrategy.canExit()) {
                    super.onBackPressed()
                } else {
                    ExitStrategy.startExitDelay(2000)
                    Toast.makeText(getActivity(), getString(R.string.exit_msg),
                            Toast.LENGTH_SHORT).show()
                }
            } else {
                bottom_navigation.setCurrentItem(0)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

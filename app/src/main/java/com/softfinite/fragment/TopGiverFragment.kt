package com.softfinite.fragment

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.softfinite.R
import com.softfinite.adapter.CharmingAdapter
import kotlinx.android.synthetic.main.activity_charming_fragment.*

/**
 * Created by Softfinite (Viv'Ek Chodvadiya) on 13-Jun-18.
 */

class TopGiverFragment : BaseFragment() {

    internal var type: Int = 0
    internal var tabSelected: Int = 0

    lateinit var charmingAdapter: CharmingAdapter
    internal lateinit var mLayoutManager: RecyclerView.LayoutManager

    companion object {
        internal val LAYOUT_ID = "layoutid"
        fun newInstance(layoutId: Int, type: Int): TopGiverFragment {
            val pane = TopGiverFragment()
            val args = Bundle()
            args.putInt(LAYOUT_ID, layoutId)
            args.putInt("type", type)
            pane.arguments = args
            return pane
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_charming_fragment, container, false)
    }

    private fun init() {


        mLayoutManager = LinearLayoutManager(getActivity())
        if (rvCharming != null) rvCharming!!.layoutManager = mLayoutManager
        charmingAdapter = CharmingAdapter(this.getActivity()!!)
        if (rvCharming != null) rvCharming!!.adapter = charmingAdapter


        llDaily.setOnClickListener {
            setDelivery(0)
        }

        llWeekly.setOnClickListener {
            setDelivery(1)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun setDelivery(selected: Int) {
        if (selected == 0) {
            if (tabSelected == 1) {
                tabSelected = 0
                tvDaily.setBackground(resources.getDrawable(R.drawable.btn_bg_tab))
                tvDaily.setTextColor(resources.getColor(R.color.white))
                tvWeekly.setBackground(null)
                tvWeekly.setTextColor(resources.getColor(R.color.white))
            }
        } else if (selected == 1) {
            if (tabSelected == 0) {
                tabSelected = 1
                tvDaily.setBackground(null)
                tvDaily.setTextColor(resources.getColor(R.color.white))
                tvWeekly.setBackground(resources.getDrawable(R.drawable.btn_bg_tab))
                tvWeekly.setTextColor(resources.getColor(R.color.white))
            }
        }
    }
}
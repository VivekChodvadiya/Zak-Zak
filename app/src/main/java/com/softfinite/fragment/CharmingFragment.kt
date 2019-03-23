package com.softfinite.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.common.view.SimpleGestureFilter
import com.softfinite.R
import com.softfinite.UserDetailActivity
import com.softfinite.adapter.CharmingAdapter
import kotlinx.android.synthetic.main.activity_charming_fragment.*

/**
 * Created by Softfinite (Viv'Ek Chodvadiya) on 13-Jun-18.
 */

class CharmingFragment : BaseFragment(), SimpleGestureFilter.SimpleGestureListener {
    override fun onDoubleTap() {
        showToast("Double Tap")
    }

    override fun onSwipe(direction: Int) {
        var str = ""

        when (direction) {
            SimpleGestureFilter.SWIPE_RIGHT -> str = "Swipe Right"
            SimpleGestureFilter.SWIPE_LEFT -> str = "Swipe Left"
            SimpleGestureFilter.SWIPE_DOWN -> str = "Swipe Down"
            SimpleGestureFilter.SWIPE_UP -> str = "Swipe Up"
        }
        showToast(str)
    }

    private var detector: SimpleGestureFilter? = null

    internal var type: Int = 0
    internal var tabSelected: Int = 0

    lateinit var charmingAdapter: CharmingAdapter
    internal lateinit var mLayoutManager: RecyclerView.LayoutManager

    companion object {
        internal val LAYOUT_ID = "layoutid"
        fun newInstance(layoutId: Int, type: Int): CharmingFragment {
            val pane = CharmingFragment()
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

        detector = SimpleGestureFilter(activity!!, this)

        mLayoutManager = LinearLayoutManager(getActivity())
        if (rvCharming != null) rvCharming!!.layoutManager = mLayoutManager
        charmingAdapter = CharmingAdapter(this.getActivity()!!)
        if (rvCharming != null) rvCharming!!.adapter = charmingAdapter

        charmingAdapter.setEventListener(object : CharmingAdapter.EventListener {
            override fun onItemViewClicked(position: Int) {
                val i = Intent(getActivity(), UserDetailActivity::class.java)
                startActivity(i)
            }
        })


//        val activitySwipeDetector = ActivitySwipeDetector(this!!.activity!!)
//        container.setOnTouchListener(activitySwipeDetector)
//
//        activitySwipeDetector.setEventListener(object : ActivitySwipeDetector.EventListener {
//            override fun onRightSwiped() {
//                Toast.makeText(activity, "RightToLeftSwipe", Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onLeftSwiped() {
//                Toast.makeText(activity, "LeftToRightSwipe", Toast.LENGTH_SHORT).show()
//            }
//        })

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
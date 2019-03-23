package com.softfinite

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.softfinite.adapter.GiftReceivedAdapter
import com.softfinite.adapter.PrivateVideosAdapter
import kotlinx.android.synthetic.main.activity_user_detail.*

/**
 * Created by Softfinite (Viv'Ek Chodvadiya) on 13-Jun-18.
 */

class UserDetailActivity : BaseActivity() {

    lateinit var giftReceivedAdapter: GiftReceivedAdapter
    lateinit var privateVideosAdapter: PrivateVideosAdapter
    internal lateinit var mLayoutManager: RecyclerView.LayoutManager
    internal lateinit var mLayoutManagerPrivateVideos: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)
        init()
    }

    private fun init() {
        mLayoutManager = LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false)
        if (tvGiftReceived != null) tvGiftReceived!!.layoutManager = mLayoutManager
        giftReceivedAdapter = GiftReceivedAdapter(this.getActivity()!!)
        if (tvGiftReceived != null) tvGiftReceived!!.adapter = giftReceivedAdapter


        mLayoutManagerPrivateVideos = LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false)
        if (rvPrivateVideos != null) rvPrivateVideos!!.layoutManager = mLayoutManagerPrivateVideos
        privateVideosAdapter = PrivateVideosAdapter(this.getActivity()!!)
        if (rvPrivateVideos != null) rvPrivateVideos!!.adapter = privateVideosAdapter
    }
}

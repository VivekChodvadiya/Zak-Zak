package com.softfinite.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.softfinite.R
import com.softfinite.utils.Utils
import com.nostra13.universalimageloader.core.ImageLoader
import kotlinx.android.synthetic.main.item_invitaion_list.view.*

/**
 * Created by Softfinite (Viv'Ek Chodvadiya) on 13-Jun-18.
 */

class HomeAdapter(internal var context: Context) : RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {

//    private var data: MutableList<Heros.Hero>? = ArrayList<Heros.Hero>()

    internal var imageLoader: ImageLoader
    internal var myEventAdapter: HomeAdapter? = null
    private lateinit var mEventListener: EventListener
    internal var isFilterable = false

    init {
        imageLoader = Utils.initImageLoader(context)!!
    }

//    fun add(mData: Heros.Hero) {
//        data!!.add(mData)
//        notifyDataSetChanged()
//    }
//
//
//    fun addAll(mData: List<Heros.Hero>) {
//        clear()
//        try {
//            this.data!!.clear()
//            this.data!!.addAll(mData)
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//        notifyDataSetChanged()
//    }
//
//    fun clear() {
//        data!!.clear()
//        notifyDataSetChanged()
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.item_invitaion_list, parent, false)
        return MyViewHolder(v)
    }

    //    public void remove(int position, int id) {
    //        mData = mData + "," + id;
    //        data.remove(position);
    //        notifyDataSetChanged();
    //    }

//    fun getItem(pos: Int): Heros.Hero {
//        return data!![pos]
//    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        val item = data!![position]
//
//        //            Debug.e("userload-->", item.userPhoto);
//        if (!item.imageurl.isNullOrEmpty()) {
//            imageLoader.displayImage(Utils.nullSafe(item.imageurl), holder.imgEvent)
//        } else {
//            holder.imgEvent.setImageDrawable(context.resources.getDrawable(R.drawable.ic_launcher_background))
//        }
////
//        holder.tvInvitationTitle!!.setText(item.name)
//
//        holder.container!!.setOnClickListener {
//            if (mEventListener != null) {
//                mEventListener.onItemViewClicked(position)
//            }
//        }
    }

    override fun getItemCount(): Int {

//        return data!!.size
                return 25;
    }

    fun setEventListener(eventListener: EventListener) {
        mEventListener = eventListener
    }

    interface EventListener {
        fun onItemViewClicked(position: Int)
    }

    class MyViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        val imgEvent = v.imgInvitation
        val container = v.container
        val tvInvitationTitle = v.tvInvitationTitle
    }
}

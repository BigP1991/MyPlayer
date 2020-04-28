package com.zhc.myplayer.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zhc.myplayer.R
import com.zhc.myplayer.model.HomeItemBean
import com.zhc.myplayer.widget.HomeItemView

/**
 * @author created by zhanghaochen
 * @date 2020-04-08 14:39
 * 描述：
 */
class HomeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val mData = ArrayList<HomeItemBean>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holder = HomeHolder(HomeItemView(parent.context))

        //        val holder = HomeHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_home, parent, false))
        return holder
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = mData[holder.adapterPosition]
        val itemView = holder.itemView as HomeItemView
        itemView.setData(data)
    }

    fun notifyDataChanged(list: List<HomeItemBean>) {
        if (!list.isNullOrEmpty()) {
            mData.clear()
            mData.addAll(list)
            notifyDataSetChanged()
        }
    }

    class HomeHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}

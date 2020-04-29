package com.zhc.myplayer.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zhc.myplayer.model.HomeItemBean
import com.zhc.myplayer.widget.HomeItemView
import com.zhc.myplayer.widget.LoadMoreView

/**
 * @author created by zhanghaochen
 * @date 2020-04-08 14:39
 * 描述：
 */
class HomeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val mData = ArrayList<HomeItemBean>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            0    -> HomeHolder(HomeItemView(parent.context))
            1    -> HomeHolder(LoadMoreView(parent.context))
            else -> HomeHolder(HomeItemView(parent.context))
        }
    }

    override fun getItemCount(): Int {
        return mData.size + 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val itemView = holder.itemView) {
            is HomeItemView -> {
                val data = mData[holder.adapterPosition]
                itemView.setData(data)
            }
            is LoadMoreView -> {

            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            // 最后一条数据显示进度条
            mData.size -> 1
            else       -> 0
        }
    }

    fun notifyDataChanged(list: List<HomeItemBean>) {
        if (!list.isNullOrEmpty()) {
            mData.clear()
            mData.addAll(list)
            notifyDataSetChanged()
        }
    }

    fun notifyDataChangedMore(list: List<HomeItemBean>) {
        if (!list.isNullOrEmpty()) {
            mData.addAll(list)
            notifyDataSetChanged()
        }
    }

    class HomeHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}

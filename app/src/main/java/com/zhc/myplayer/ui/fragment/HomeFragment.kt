package com.zhc.myplayer.ui.fragment

import android.os.Bundle
import android.os.Message
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.zhc.myplayer.R
import com.zhc.myplayer.adapter.HomeAdapter
import com.zhc.myplayer.base.BaseFragment
import com.zhc.myplayer.model.HomeItemBean
import com.zhc.myplayer.util.URLProviderUtil
import com.zhc.myplayer.widget.HomeItemView
import okhttp3.*
import org.jetbrains.anko.find
import java.io.IOException
import java.lang.Exception

/**
 * @author created by zhanghaochen
 * @date 2020-04-07 15:47
 * 描述：
 */
@Suppress("UNCHECKED_CAST")
class HomeFragment : BaseFragment() {
    private val mAdapter: HomeAdapter by lazy {
        HomeAdapter()
    }

    override fun handleMessage(message: Message) {
        when (message.what) {
            0x100 -> {
                val data = message.obj
                mAdapter.notifyDataChanged(data as List<HomeItemBean>)
            }
        }
    }

    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        return view
    }

    override fun initListener(mainView: View?) {
        val recyclerView = mainView?.find<RecyclerView>(R.id.home_recycler)
        recyclerView?.layoutManager = object : LinearLayoutManager(context) {
            override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
                try {
                    super.onLayoutChildren(recycler, state)
                } catch (ignored: Exception) {
                }
            }
        }

        recyclerView?.adapter = mAdapter

    }

    override fun initData() {
        loadData()
    }

    private fun loadData() {
        val path = URLProviderUtil.getHomeUrl(0, 20)
        val client = OkHttpClient()
        val request = Request.Builder()
                .url(path)
                .get()
                .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                myToast("失败")
            }

            override fun onResponse(call: Call, response: Response) {
                val result = response.body?.string()
                myToast("成功 \r\n $result")

                val jsonStr = activity?.resources?.getString(R.string.home_reponse)
                val gson = Gson()
                val homeBean = gson.fromJson<List<HomeItemBean>>(jsonStr, object : TypeToken<List<HomeItemBean>>() {}.type)
                mHandler.obtainMessage(0x100, homeBean).sendToTarget()
                Log.d("zhc", "长度：" + homeBean.size)
            }

        })
    }
}
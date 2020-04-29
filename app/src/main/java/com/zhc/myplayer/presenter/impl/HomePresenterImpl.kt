package com.zhc.myplayer.presenter.impl

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.zhc.myplayer.R
import com.zhc.myplayer.model.HomeItemBean
import com.zhc.myplayer.presenter.`interface`.HomePresenter
import com.zhc.myplayer.util.URLProviderUtil
import com.zhc.myplayer.view.HomeView
import okhttp3.*
import java.io.IOException

/**
 * @author created by zhanghaochen
 * @date 2020-04-29 16:12
 * 描述：home界面的p层具体实现类，view和presenter是互相绑定的
 */
class HomePresenterImpl(val homeView: HomeView) : HomePresenter {
    override fun loadData(isRefreshAll: Boolean) {
        val path = URLProviderUtil.getHomeUrl(0, 20)
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(path)
            .get()
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                homeView.loadFail("读取失败")
            }

            override fun onResponse(call: Call, response: Response) {
                val jsonStr = homeView.getBaseActivity()?.resources?.getString(R.string.home_reponse)
                val gson = Gson()
                val homeBean = gson.fromJson<List<HomeItemBean>>(jsonStr, object : TypeToken<List<HomeItemBean>>() {}.type)
                if (isRefreshAll) {
                    homeView.loadAllSuccess(homeBean)
                } else {
                    homeView.loadMoreSuccess(homeBean)
                }
                Log.d("zhc", "长度：" + homeBean.size)
            }

        })
    }
}

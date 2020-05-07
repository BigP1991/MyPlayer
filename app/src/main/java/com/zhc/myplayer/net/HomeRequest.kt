package com.zhc.myplayer.net

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.zhc.myplayer.R
import com.zhc.myplayer.base.App
import com.zhc.myplayer.model.HomeItemBean
import com.zhc.myplayer.util.URLProviderUtil

/**
 * @author created by zhanghaochen
 * @date 2020-05-06 16:09
 * 描述：
 */
class HomeRequest(offSet: Int, size: Int, response: ResponseHandler<List<HomeItemBean>>) :
    BaseRequest<List<HomeItemBean>>(URLProviderUtil.getHomeUrl(offSet, size), response) {

    override fun parseResult(result: String?): List<HomeItemBean> {
        val jsonStr = App.instance?.applicationContext?.resources?.getString(R.string.home_reponse)
        val gson = Gson()
        return gson.fromJson(jsonStr, object : TypeToken<List<HomeItemBean>>() {}.type)
    }


}

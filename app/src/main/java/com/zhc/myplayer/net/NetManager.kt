package com.zhc.myplayer.net

import okhttp3.*
import java.io.IOException

/**
 * @author created by zhanghaochen
 * @date 2020-05-06 15:46
 * 描述：发送请求的基类
 */
class NetManager private constructor() {
    private val client by lazy { OkHttpClient() }

    companion object {
        val manager by lazy {
            NetManager()
        }
    }

    /**
     * 发送请求
     */
    fun <T> sendRequest(req: BaseRequest<T>) {
        //        val path = URLProviderUtil.getHomeUrl(0, 20)

        val request = Request.Builder()
            .url(req.url)
            .get()
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                req.response.onError(e.message)
                //                homeView.loadFail("读取失败")
            }

            override fun onResponse(call: Call, response: Response) {
                val result = req.parseResult("mockHome")
                req.response.onSuccess(result)
            }

        })
    }
}

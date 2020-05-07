package com.zhc.myplayer.net

/**
 * @author created by zhanghaochen
 * @date 2020-05-06 15:40
 * 描述：
 */
abstract class BaseRequest<T>(val url: String, val response: ResponseHandler<T>) {
    // 获取泛型的类型
    //    protected val type = (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]

    /**
     * 解析网络请求结果
     */
    abstract fun parseResult(result: String?): T

    fun sendRequest() {
        NetManager.manager.sendRequest(this)
    }
}

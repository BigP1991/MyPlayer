package com.zhc.myplayer.net

/**
 * @author created by zhanghaochen
 * @date 2020-05-06 15:43
 * 描述：请求回调
 */
interface ResponseHandler<T> {
    fun onError(msg: String?)
    fun onSuccess(result: T)
}

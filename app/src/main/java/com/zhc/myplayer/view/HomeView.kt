package com.zhc.myplayer.view

import com.zhc.myplayer.base.BaseActivity
import com.zhc.myplayer.model.HomeItemBean

/**
 * @author created by zhanghaochen
 * @date 2020-04-29 16:11
 * 描述：mvp架构，home界面接口
 */
interface HomeView {
    fun loadAllSuccess(data: List<HomeItemBean>)
    fun loadMoreSuccess(data: List<HomeItemBean>)
    fun loadFail(string: String)
    fun getBaseActivity(): BaseActivity?
}

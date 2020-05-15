package com.zhc.myplayer.presenter.impl

import com.zhc.myplayer.model.HomeItemBean
import com.zhc.myplayer.net.HomeRequest
import com.zhc.myplayer.net.ResponseHandler
import com.zhc.myplayer.presenter.`interface`.HomePresenter
import com.zhc.myplayer.view.HomeView

/**
 * @author created by zhanghaochen
 * @date 2020-04-29 16:12
 * 描述：home界面的p层具体实现类，view和presenter是互相绑定的
 */
class HomePresenterImpl(var homeView: HomeView?) : HomePresenter {

    fun destroyView() {
        if (homeView != null) {
            homeView = null
        }
    }

    fun bindView(homeView: HomeView?) {
        this.homeView = homeView
    }

    override fun loadData(isRefreshAll: Boolean) {
        // 定义一个request
        val homeRequest = HomeRequest(0, 20, object : ResponseHandler<List<HomeItemBean>> {
            override fun onError(msg: String?) {
                homeView?.loadFail("读取失败")
            }

            override fun onSuccess(result: List<HomeItemBean>) {
                if (isRefreshAll) {
                    homeView?.loadAllSuccess(result)
                } else {
                    homeView?.loadMoreSuccess(result)
                }
            }
        }).sendRequest()
        // 发送request
    }
}

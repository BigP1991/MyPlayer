package com.zhc.myplayer.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.zhc.myplayer.R
import com.zhc.myplayer.adapter.HomeAdapter
import com.zhc.myplayer.base.BaseActivity
import com.zhc.myplayer.base.BaseFragment
import com.zhc.myplayer.model.HomeItemBean
import com.zhc.myplayer.presenter.impl.HomePresenterImpl
import com.zhc.myplayer.view.HomeView
import org.jetbrains.anko.find

/**
 * @author created by zhanghaochen
 * @date 2020-04-07 15:47
 * 描述：
 */
@Suppress("UNCHECKED_CAST")
class HomeFragment : BaseFragment(), HomeView {
    companion object {
        const val HANDLER_REFRESH = 0x100
        const val HANDLER_END_REFRESH = 0x101
        const val HANDLER_LOAD_MORE = 0x102
    }

    private val mAdapter: HomeAdapter by lazy {
        HomeAdapter()
    }

    private val mPresenter by lazy {
        HomePresenterImpl(this)
    }

    private lateinit var mRefreshLayout: SwipeRefreshLayout

    override fun handleMessage(message: Message) {
        when (message.what) {
            HANDLER_REFRESH     -> {
                val data = message.obj
                mAdapter.notifyDataChanged(data as List<HomeItemBean>)
                mHandler.obtainMessage(HANDLER_END_REFRESH).sendToTarget()
            }
            HANDLER_LOAD_MORE   -> {
                val data = message.obj
                mAdapter.notifyDataChangedMore(data as List<HomeItemBean>)
                mHandler.obtainMessage(HANDLER_END_REFRESH).sendToTarget()
            }
            HANDLER_END_REFRESH -> {
                mRefreshLayout.isRefreshing = false
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
        recyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                // 判断是否是闲置状态下的最后一条数据
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (recyclerView.layoutManager is LinearLayoutManager) {
                        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                        val lastIndex = layoutManager.findLastCompletelyVisibleItemPosition()
                        if (lastIndex == layoutManager.itemCount - 1) {
                            mPresenter.loadData(false)
                        }
                    }
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            }
        })

        // 初始化刷新控件
        mRefreshLayout = mainView?.find(R.id.home_refresh_layout)!!
        mRefreshLayout.setColorSchemeColors(Color.RED, Color.GREEN)
        mRefreshLayout.setOnRefreshListener {
            mPresenter.loadData(true)
        }
    }

    override fun loadAllSuccess(data: List<HomeItemBean>) {
        myToast("读取成功")
        mHandler.obtainMessage(HANDLER_REFRESH, data).sendToTarget()
    }

    override fun loadMoreSuccess(data: List<HomeItemBean>) {
        myToast("读取成功")
        mHandler.obtainMessage(HANDLER_LOAD_MORE, data).sendToTarget()
    }

    override fun loadFail(string: String) {
        myToast(string)
        mHandler.obtainMessage(HANDLER_END_REFRESH).sendToTarget()
    }

    override fun getBaseActivity(): BaseActivity? {
        return activity as BaseActivity
    }

    override fun initData() {
        mPresenter.loadData(true)
    }
}
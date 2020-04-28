package com.zhc.myplayer.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.roughike.bottombar.BottomBar
import com.zhc.myplayer.R
import com.zhc.myplayer.base.BaseActivity
import com.zhc.myplayer.util.FragmentUtil
import com.zhc.myplayer.util.ToolBarManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.find

class MainActivity : BaseActivity(), ToolBarManager {
    // 懒加载，用的时候才会初始化，并且是线程安全的
    override val mToolBar by lazy {
        find<Toolbar>(R.id.toolbar)
    }

    val mBottomBar by lazy {
        find<BottomBar>(R.id.main_bottombar)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initViews() {
        initMainToolBar()
    }

    override fun initListener() {
        // 设置bottombar的监听
        mBottomBar.setOnTabSelectListener { tabId: Int ->
            // 这里的tabId的值打印其实是0，1，2，3，4
            // 获取fragmentManager的transaction
            val transaction = supportFragmentManager.beginTransaction()
            FragmentUtil.fragmentUtil.getFragment(tabId)?.let {
                transaction.replace(R.id.main_container, it)
                transaction.commit()
            }
        }
    }
}

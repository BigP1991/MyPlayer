package com.zhc.myplayer.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.zhc.myplayer.R
import com.zhc.myplayer.base.BaseActivity
import com.zhc.myplayer.util.ToolBarManager
import org.jetbrains.anko.find

class MainActivity : BaseActivity(), ToolBarManager {
    // 懒加载，用的时候才会初始化，并且是线程安全的
    override val mToolBar by lazy {
        find<Toolbar>(R.id.toolbar)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initViews() {
        initMainToolBar()
    }
}

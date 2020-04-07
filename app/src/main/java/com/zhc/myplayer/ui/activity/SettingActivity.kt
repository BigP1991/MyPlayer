package com.zhc.myplayer.ui.activity

import android.content.SharedPreferences
import androidx.appcompat.widget.Toolbar
import com.zhc.myplayer.R
import com.zhc.myplayer.base.BaseActivity
import com.zhc.myplayer.util.SpUtil
import com.zhc.myplayer.util.ToolBarManager
import org.jetbrains.anko.find

/**
 * @author created by zhanghaochen
 * @date 2020-03-30 16:52
 * 描述：设置界面
 */
class SettingActivity : BaseActivity(), ToolBarManager {
    override val mToolBar by lazy {
        find<Toolbar>(R.id.toolbar)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_setting
    }

    override fun initViews() {
        initSettingToolBar()
    }
}
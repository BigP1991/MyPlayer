package com.zhc.myplayer.util

import android.content.Intent
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.zhc.myplayer.R
import com.zhc.myplayer.ui.activity.SettingActivity

/**
 * @author created by zhanghaochen
 * @date 2020-03-30 14:35
 * 描述：工具栏管理类
 */
// 这个kotlin的interface比较特别，他里面的类是可以有实现的
interface ToolBarManager {

    val mToolBar: Toolbar

    /**
     * 初始化主界面的toolbar
     */
    fun initMainToolBar() {
        this.mToolBar.title = "我的影音"

        mToolBar.inflateMenu(R.menu.main)

        // 如果java接口中只有一个未实现的方法，kotlin可以省略接口对象，直接调用复写的方法
        mToolBar.setOnMenuItemClickListener { it ->
            when (it.itemId) {
                R.id.setting -> {
                    // 跳转到设置页面
                    mToolBar.context.startActivity(Intent(mToolBar.context, SettingActivity::class.java))
                }
            }
            true
        }
        //        mToolBar.setOnMenuItemClickListener(object : Toolbar.OnMenuItemClickListener {
        //            override fun onMenuItemClick(item: MenuItem?): Boolean {
        //
        //            }
        //        })
    }

    fun initSettingToolBar(){
        this.mToolBar.title = "设置"
    }
}

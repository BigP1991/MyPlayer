package com.zhc.myplayer.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.toast

/**
 * @author created by zhanghaochen
 * @date 2020-03-26 16:54
 * 描述：所有activity的基类
 */
abstract class BaseActivity : AppCompatActivity(), AnkoLogger {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initViews()
        initListener()
        initData()

        //        debug { "onCreate" }
    }

    /**
     * activity的布局Id
     */
    abstract fun getLayoutId(): Int

    /**
     * 初始化adapter以及listener
     */
    open protected fun initListener() {

    }

    /**
     * 初始化数据
     */
    open protected fun initData() {

    }

    open protected fun initViews() {

    }

    open protected fun myToast(msg: String) {
        runOnUiThread {
            toast(msg)
        }
    }
}
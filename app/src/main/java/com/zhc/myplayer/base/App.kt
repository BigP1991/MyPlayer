package com.zhc.myplayer.base

import android.app.Application

/**
 * @author created by zhanghaochen
 * @date 2020-04-01 16:34
 * 描述：自定义Application
 */
class App : Application() {
    companion object {
        var instance: App? = null

        fun getContext() = instance!!
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
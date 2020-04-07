package com.zhc.myplayer.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.toast

/**
 * @author created by zhanghaochen
 * @date 2020-03-27 10:53
 * 描述：所有fragment的基类
 */
abstract class BaseFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val mainView = initView(inflater, container, savedInstanceState)
        initListener(mainView)
        return mainView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData()
    }

    abstract fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?

    open protected fun initData() {

    }

    open protected fun initListener(mainView: View?) {

    }

    open protected fun init() {

    }

    open protected fun myToast(msg: CharSequence) {
        context?.runOnUiThread {
            toast(msg)
        }
    }
}

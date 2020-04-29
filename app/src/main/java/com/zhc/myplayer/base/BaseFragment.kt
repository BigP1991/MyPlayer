package com.zhc.myplayer.base

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.toast
import java.lang.ref.WeakReference

/**
 * @author created by zhanghaochen
 * @date 2020-03-27 10:53
 * 描述：所有fragment的基类
 */
abstract class BaseFragment : Fragment() {
    protected val mHandler: MyHandler = MyHandler(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val mainView = initView(inflater, container, savedInstanceState)
        return mainView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener(view)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData()
    }

    protected class MyHandler(fragment: BaseFragment) : Handler() {

        private val mFragment: WeakReference<BaseFragment> = WeakReference(fragment)

        override fun handleMessage(msg: Message) {
            val fragment = mFragment.get()
            if (fragment != null && fragment.isAdded) {
                fragment.handleMessage(msg)
            }
            super.handleMessage(msg)
        }
    }

    abstract fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?

    protected abstract fun handleMessage(message: Message)

    protected open fun initData() {

    }

    protected open fun initListener(mainView: View?) {

    }

    protected open fun init() {

    }

    open protected fun myToast(msg: CharSequence) {
        context?.runOnUiThread {
            toast(msg)
        }
    }
}

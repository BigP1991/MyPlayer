package com.zhc.myplayer.ui.fragment

import android.os.Bundle
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zhc.myplayer.R
import com.zhc.myplayer.base.BaseFragment

/**
 * @author created by zhanghaochen
 * @date 2020-04-07 15:49
 * 描述：
 */
class MvFragment : BaseFragment() {
    override fun handleMessage(message: Message) {

    }

    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_mv, container, false)

        return view
    }
}
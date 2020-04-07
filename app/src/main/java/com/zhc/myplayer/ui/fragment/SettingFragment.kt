package com.zhc.myplayer.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import com.zhc.myplayer.R
import com.zhc.myplayer.base.BaseFragment
import com.zhc.myplayer.ui.activity.AboutActivity
import com.zhc.myplayer.ui.activity.MainActivity
import com.zhc.myplayer.util.SpUtil
import org.jetbrains.anko.find

/**
 * @author created by zhanghaochen
 * @date 2020-03-31 17:19
 * 描述：
 */
class SettingFragment : BaseFragment(), View.OnClickListener {

    private lateinit var switchNotify: Switch
    lateinit var switchWifi: Switch

    var wifiCheckState by SpUtil("wifiSwitch", false)
    var notifyCheckState by SpUtil("notifySwitch", false)

    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_setting, container)

        switchNotify = view.find(R.id.notify_switch)
        switchWifi = view.find(R.id.wifi_switch)

        switchNotify.isChecked = notifyCheckState
        switchWifi.isChecked = wifiCheckState
        return view
    }

    override fun initListener(mainView: View?) {
        mainView?.find<TextView>(R.id.about)?.setOnClickListener(this)
        mainView?.find<ViewGroup>(R.id.notify_layout)?.setOnClickListener(this)
        mainView?.find<ViewGroup>(R.id.wifi_layout)?.setOnClickListener(this)

        switchNotify.setOnCheckedChangeListener { _, checked ->
            notifyCheckState = checked
        }
        switchWifi.setOnCheckedChangeListener { _, checked ->
            wifiCheckState = checked
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.about         ->
                startActivity(Intent(context, AboutActivity::class.java))
            R.id.notify_layout ->
                switchNotify.isChecked = !switchNotify.isChecked
            R.id.wifi_layout   ->
                switchWifi.isChecked = !switchWifi.isChecked
        }
    }
}
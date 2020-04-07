package com.zhc.myplayer.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.zhc.myplayer.base.App
import java.lang.IllegalArgumentException
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * @author created by zhanghaochen
 * @date 2020-04-01 16:16
 * 描述：SharedPreference工具类，可以使用委托的方法使用
 */
class SpUtil<T>(private val key: String,
                private val defaultValue: T,
                private val spName: String = "defaultSp",
                private val useCommit: Boolean = false) : ReadWriteProperty<Any?, T> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return getSharePreferences(key, defaultValue)
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        putSharePreferences(key, value)
    }

    // 懒加载获取实例
    private val prefs: SharedPreferences by lazy {
        App.getContext().getSharedPreferences(spName, Context.MODE_PRIVATE)
    }

    @SuppressLint("ApplySharedPref")
    private fun putSharePreferences(name: String, value: T) = with(prefs.edit()) {
        val editor = when (value) {
            is Long    -> putLong(name, value)
            is String  -> putString(name, value)
            is Int     -> putInt(name, value)
            is Boolean -> putBoolean(name, value)
            is Float   -> putFloat(name, value)
            else       -> throw IllegalArgumentException("Type Error, cannot be saved!")
        }
        if (useCommit) {
            editor.commit()
        } else {
            editor.apply()
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun getSharePreferences(name: String, default: T): T = with(prefs) {
        val result: Any = when (default) {
            is Long    -> getLong(name, default)
            is String  -> getString(name, default) ?: ""
            is Int     -> getInt(name, default)
            is Boolean -> getBoolean(name, default)
            is Float   -> getFloat(name, default)
            else       -> throw IllegalArgumentException("Type Error, cannot get!")
        }
        return result as T
    }
}
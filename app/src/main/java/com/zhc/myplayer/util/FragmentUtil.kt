package com.zhc.myplayer.util

import com.zhc.myplayer.R
import com.zhc.myplayer.base.BaseFragment
import com.zhc.myplayer.ui.fragment.HomeFragment
import com.zhc.myplayer.ui.fragment.MusicListFragment
import com.zhc.myplayer.ui.fragment.MvFragment
import com.zhc.myplayer.ui.fragment.RankingFragment

/**
 * @author created by zhanghaochen
 * @date 2020-04-07 15:42
 * 描述：
 */
// private constructior表示私有化构造方法，一般用于单例模式
class FragmentUtil private constructor() {
    val homeFragment by lazy { HomeFragment() }
    val mvFragment by lazy { MvFragment() }
    val musicListFragment by lazy { MusicListFragment() }
    val rankingFragment by lazy { RankingFragment() }

    companion object {
        val fragmentUtil by lazy {
            FragmentUtil()
        }
    }

    fun getFragment(tabId: Int): BaseFragment? {
        return when (tabId) {
            // 这个地方的id不能加android，否则和bottombar监听中的tabId会不相同
            R.id.tab_home    ->
                homeFragment
            R.id.tab_mv      ->
                mvFragment
            R.id.tab_collect ->
                musicListFragment
            R.id.tab_rank    ->
                rankingFragment
            else             ->
                null
        }
    }
}
package com.zhc.myplayer.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.zhc.myplayer.R

/**
 * @author created by zhanghaochen
 * @date 2020-05-07 15:14
 * 描述：
 */
class MusicListItemView : FrameLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        View.inflate(context, R.layout.item_music_list, this)
    }
}

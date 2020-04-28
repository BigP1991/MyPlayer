package com.zhc.myplayer.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.RelativeLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.zhc.myplayer.R
import com.zhc.myplayer.model.HomeItemBean
import kotlinx.android.synthetic.main.item_home.view.*

/**
 * @author created by zhanghaochen
 * @date 2020-04-07 17:37
 * 描述：
 */
class HomeItemView : FrameLayout {

    // 重要知识点：次构造函数需要委托主构造函数，使用 ：this来委托给主构造函数
    // init代码块先于次构造函数调用，调用顺序：主构造->init代码块->次构造
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    // 构造函数调用时都会调用到init
    init {
        View.inflate(context, R.layout.item_home, this)
    }

    fun setData(data: HomeItemBean) {
        home_item_title.text = data.title
        home_item_sub_title.text = data.description
        Glide.with(context).load("file:///android_asset/${data.posterPic}").into(home_item_bg)
    }
}

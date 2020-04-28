package com.zhc.myplayer.model

/**
 * @author created by zhanghaochen
 * @date 2020-04-10 14:32
 * 描述：
 */
data class HomeItemBean(
    val clickUrl: String,
    val description: String,
    val hdUrl: String,
    val hdVideoSize: Int,
    val id: Int,
    val posterPic: String,
    val status: Int,
    val subType: String,
    val thumbnailPic: String,
    val title: String,
    val traceUrl: String,
    val type: String,
    val uhdUrl: String,
    val uhdVideoSize: Int,
    val url: String,
    val videoSize: Int
)

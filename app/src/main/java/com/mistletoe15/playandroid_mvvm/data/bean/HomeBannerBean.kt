package com.mistletoe15.playandroid_mvvm.data.bean

/**
 * 首页banner item 内容实体bean
 * Created by Mistletoe on 2020/5/29
 **/
 data class HomeBannerBean(
    var desc:String = "",
    var id:Int=29,
    var imagePath:String="",
    var isVisible:Int = 0,
    var order:Int = 0,
    var title:String = "",
    var type:Int = 0,
    var url:String =""
)
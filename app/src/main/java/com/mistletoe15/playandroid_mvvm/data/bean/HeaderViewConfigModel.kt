package com.mistletoe15.playandroid_mvvm.data.bean

import android.graphics.drawable.Drawable

/**
 * Created by Mistletoe on 2020/6/8
 **/
data  class HeaderViewConfigModel (
     var title: String = "",
     var bgColor:Int = 0,
     var enableLeft :Boolean = false,
     var enableRight :Boolean = false,
     var leftImage : Drawable? = null,
     var rightImage  : Drawable? = null,
     var onLeftViewClickListener:()-> Unit,
     var onRightViewClickListener:()-> Unit
)
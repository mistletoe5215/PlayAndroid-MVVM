package com.mistletoe15.playandroid_mvvm.ui.widget

import android.widget.LinearLayout
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import android.widget.ImageView
import android.widget.TextView
import com.mistletoe15.architecture.util.DeviceUtil
import com.mistletoe15.playandroid_mvvm.R
import com.mistletoe15.playandroid_mvvm.utils.OnRetryAction
/**
 * Created by Mistletoe on 2020/7/13
 **/
class LoadingFailedView:LinearLayout{
    private var tvText:TextView?=null
    private var img:ImageView?=null
    constructor(context: Context):super(context){
        initView(context)
    }
    constructor(context: Context,attributes: AttributeSet):super(context,attributes){
        initView(context)
    }
    fun setFailedImage(drawable: Drawable):LoadingFailedView{
        img?.setImageDrawable(drawable)
        return this
    }
    fun setFailedText(failedText:String):LoadingFailedView{
        tvText?.text = failedText
        return this
    }
    fun setOnRetryAction(onRetryAction:OnRetryAction):LoadingFailedView{
       setOnClickListener {
            onRetryAction()
        }
        return this
    }
    private fun initView(context: Context){
        setPadding(0,300,0,20)
        gravity = Gravity.CENTER_HORIZONTAL
        orientation = VERTICAL
         tvText = TextView(context)
         img = ImageView(context)
        val imgLp =  LayoutParams(
            DeviceUtil.dip2px(context, 300f),
            DeviceUtil.dip2px(context, 200f)
        )
        img?.layoutParams = imgLp
        val tvLp =  LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        tvLp.topMargin  =  DeviceUtil.dip2px(context, 50f)
        tvText?.setTextAppearance(context,R.style.text_medium_size_bold_blue)
        tvText?.layoutParams = tvLp
        addView(img)
        addView(tvText)
    }
}

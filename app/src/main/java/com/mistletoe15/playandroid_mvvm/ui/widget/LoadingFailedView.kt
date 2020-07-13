package com.mistletoe15.playandroid_mvvm.ui.widget

import android.widget.LinearLayout
import android.content.Context
import android.graphics.Canvas
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
    constructor(context: Context):super(context){
        initView(context)
    }
    constructor(context: Context,attributes: AttributeSet):super(context,attributes){
        initView(context)
    }
    private var _backgroundImage:Drawable?=null
    private var _failedText:String?=null
    private var _onRetryAction:OnRetryAction?=null
    fun setFailedImage(drawable: Drawable):LoadingFailedView{
        this._backgroundImage = drawable
        return this
    }
    fun setFailedText(failedText:String):LoadingFailedView{
        this._failedText = failedText
        return this
    }
    fun setOnRetryAction(onRetryAction:OnRetryAction):LoadingFailedView{
        this._onRetryAction = onRetryAction
        return this
    }
    fun emit():Boolean{
        if(this._backgroundImage!=null && this._failedText!=null && this._onRetryAction!=null){
            invalidate()
            return  true
        }
        return false
    }
    private fun initView(context: Context){
        setPadding(0,300,0,20)
        gravity = Gravity.CENTER_HORIZONTAL
        orientation = VERTICAL
        val tvText = TextView(context)
        val img = ImageView(context)
        val imgLp =  LayoutParams(
            DeviceUtil.dip2px(context, 300f),
            DeviceUtil.dip2px(context, 200f)
        )
        img.layoutParams = imgLp
        val tvLp =  LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        tvLp.topMargin  =  DeviceUtil.dip2px(context, 50f)
        tvText.setTextAppearance(context,R.style.text_medium_size_bold_33)
        tvText.layoutParams = tvLp
        addView(img)
        addView(tvText)
        _failedText?.let { tvText.text = it }
        _backgroundImage?.let { img.setImageDrawable(it) }
        tvText.setOnClickListener {
            this._onRetryAction?.let { it() }
        }
    }
}

package com.mistletoe15.playandroid_mvvm.ui.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.mistletoe15.architecture.util.DeviceUtil
import com.mistletoe15.playandroid_mvvm.R
import com.mistletoe15.playandroid_mvvm.data.bean.HeaderViewConfigBean

/**
 * Created by Mistletoe on 2020/6/8
 **/
@SuppressLint("Recycle")
class HeaderView(context: Context?) : RelativeLayout(context) {
    private var mBgColor:Int = 0
    private var mTitle :String? = null
    private var hasLeftView :Boolean = false
    private var hasRightView :Boolean = false
    private var leftView :Drawable? = null
    private var rightView :Drawable? = null
    private var mLeftClickListener:()->Unit = {}
    private var mRightClickListener:()->Unit = {}
    init {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT)
    }
    @SuppressLint("InflateParams")
    private fun initView(context: Context?){
       if(mBgColor!=0){
           setBackgroundColor(mBgColor)
       }
       mTitle?.let {
            val mTitleText =  TextView(context)
            val lp = LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT)
            lp.addRule(CENTER_IN_PARENT)
            lp.addRule(CENTER_VERTICAL)
            mTitleText.layoutParams = lp
            mTitleText.setTextAppearance(context,R.style.header_text)
            mTitleText.text = it
            addView(mTitleText)
        }
        leftView?.let {
            if(hasLeftView){
                val leftImgBtn = ImageView(context)
                val lp = LayoutParams(DeviceUtil.dip2px(context,40f),DeviceUtil.dip2px(context,40f))
                lp.addRule(ALIGN_PARENT_LEFT)
                lp.addRule(CENTER_VERTICAL)
                leftImgBtn.layoutParams = lp
                leftImgBtn.setImageDrawable(it)
                leftImgBtn.setOnClickListener {
                    mLeftClickListener()
                }
                addView(leftImgBtn)
            }
        }
        rightView?.let {
            if(hasRightView){
                val rightImgBtn = ImageView(context)
                val lp = LayoutParams(DeviceUtil.dip2px(context,40f),DeviceUtil.dip2px(context,40f))
                lp.addRule(ALIGN_PARENT_RIGHT)
                lp.addRule(CENTER_VERTICAL)
                rightImgBtn.layoutParams = lp
                rightImgBtn.setImageDrawable(it)
                rightImgBtn.setOnClickListener {
                    mRightClickListener()
                }
                addView(rightImgBtn)
            }
        }
    }
    fun setHeaderConfig(headerConfig: HeaderViewConfigBean?):HeaderView{
         headerConfig?.apply {
             mLeftClickListener = onLeftViewClickListener
             mRightClickListener = onRightViewClickListener
             mBgColor = bgColor
             mTitle = title
             hasLeftView = enableLeft
             leftView = leftImage
             hasRightView = enableRight
             rightView = rightImage
         }
        initView(context)
        return  this
    }
}
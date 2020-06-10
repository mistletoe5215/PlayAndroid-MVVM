package com.mistletoe15.playandroid_mvvm.ui.widget

import android.util.AttributeSet
import android.widget.VideoView
import android.content.Context
import android.media.MediaPlayer
import android.view.KeyEvent

/**
 * Created by Mistletoe on 2020/6/10
 **/
class MVideoView :VideoView{
    constructor(context: Context?) : this(context,null)
    constructor(context: Context?,attrs: AttributeSet?) : this(context,attrs,0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = getDefaultSize(0,widthMeasureSpec)
        val height = getDefaultSize(0,heightMeasureSpec)
        setMeasuredDimension(width,height)
    }
    override fun setOnPreparedListener(l: MediaPlayer.OnPreparedListener?) {
        super.setOnPreparedListener(l)
    }
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return super.onKeyDown(keyCode, event)
    }
}
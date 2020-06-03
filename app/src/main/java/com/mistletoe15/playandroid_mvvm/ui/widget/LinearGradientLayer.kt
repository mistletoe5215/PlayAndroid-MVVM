package com.mistletoe15.playandroid_mvvm.ui.widget
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Shader
import android.util.AttributeSet
import android.view.View
import com.mistletoe15.playandroid_mvvm.R
import kotlin.properties.Delegates
class LinearGradientLayer (context: Context?, attrs: AttributeSet?) : View(context, attrs){
    private var mPaint by Delegates.notNull<Paint>()
    private var isHorizontal by Delegates.notNull<Boolean>()
    private var mStartColor by Delegates.notNull<Int>()
    private var mEndColor by Delegates.notNull<Int>()
    private var lg by Delegates.notNull<LinearGradient>()

    init {
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        val array = context?.obtainStyledAttributes(attrs, R.styleable.LinearGradientLayer)
        isHorizontal = array?.getBoolean(R.styleable.LinearGradientLayer_LinearGradientLayerIsHorizontal, true)!!
        mStartColor = array.getColor(R.styleable.LinearGradientLayer_LinearGradientLayerStartColor,0)
        mEndColor = array.getColor(R.styleable.LinearGradientLayer_LinearGradientLayerEndColor,0)
        array.recycle()
    }
    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        lg = LinearGradient(
            0.toFloat(),
            0.toFloat(),
            if (isHorizontal) width.toFloat() else 0.toFloat(),
            if (isHorizontal) 0.toFloat() else height.toFloat(),
            mStartColor,
            mEndColor,
            Shader.TileMode.MIRROR
        )
        mPaint.shader = lg
        canvas?.drawRect(0.toFloat(), 0.toFloat(), width.toFloat(), height.toFloat(), mPaint)
    }

}
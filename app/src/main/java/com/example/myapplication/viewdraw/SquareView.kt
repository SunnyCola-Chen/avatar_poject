package com.example.myapplication.viewdraw

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import kotlin.math.min

class SquareView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : View(context, attrs) {

    // 默认大小（当父 View 是 wrap_content 且 mode 是 AT_MOST 时使用）
    private val defaultSize = 200.dpToPx()

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        // 拿到模式和尺寸
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)

        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        // 计算宽高，初始值为默认值
        var measuredWidth = defaultSize
        var measuredHeight = defaultSize

        // 处理宽
        measuredWidth = when (widthMode) {
            MeasureSpec.EXACTLY -> widthSize
            MeasureSpec.AT_MOST -> min(defaultSize, widthSize)
            MeasureSpec.UNSPECIFIED -> defaultSize
            else -> defaultSize
        }

        // 处理高
        measuredHeight = when (heightMode) {
            MeasureSpec.EXACTLY -> heightSize
            MeasureSpec.AT_MOST -> min(defaultSize, heightSize)
            MeasureSpec.UNSPECIFIED -> defaultSize
            else -> defaultSize
        }

        // 正方形：取最小的
        val size = min(measuredWidth, measuredHeight)

        // 设置测量结果
        setMeasuredDimension(size, size)
    }

    // 可选：绘制红色正方形背景，便于观察
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawColor(Color.RED)
    }

    // dp → px 扩展函数（方便设定默认大小）
    private fun Int.dpToPx(): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, this.toFloat(),
            resources.displayMetrics
        ).toInt()
    }
}

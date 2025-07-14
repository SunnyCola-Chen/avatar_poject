package com.example.myapplication.dashhoardview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import kotlin.math.cos
import kotlin.math.sin

class DashboardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    private val arcPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.LTGRAY
        strokeWidth = 20f
        style = Paint.Style.STROKE
    }

    private val tickPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.DKGRAY
        strokeWidth = 4f
    }

    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        textSize = 36f
        textAlign = Paint.Align.CENTER
    }

    private val pointerPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.RED
        strokeWidth = 8f
    }

    var maxValue = 100
    var currentValue = 0
        set(value) {
            field = value.coerceIn(0, maxValue)
            invalidate()
        }

    private val startAngle = 135f
    private val sweepAngle = 270f

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val radius = width.coerceAtMost(height) / 2f - 40f
        val centerX = width / 2f
        val centerY = height / 2f
        val rect = RectF(centerX - radius, centerY - radius, centerX + radius, centerY + radius)

        // 1. 绘制弧形
        canvas.drawArc(rect, startAngle, sweepAngle, false, arcPaint)

        // 2. 绘制刻度和文字
        val tickCount = 10
        for (i in 0..tickCount) {
            val angle = Math.toRadians((startAngle + i * sweepAngle / tickCount).toDouble())
            val xStart = centerX + (radius - 30) * cos(angle).toFloat()
            val yStart = centerY + (radius - 30) * sin(angle).toFloat()
            val xEnd = centerX + radius * cos(angle).toFloat()
            val yEnd = centerY + radius * sin(angle).toFloat()
            canvas.drawLine(xStart, yStart, xEnd, yEnd, tickPaint)

            val label = (maxValue * i / tickCount).toString()
            val xText = centerX + (radius - 60) * cos(angle).toFloat()
            val yText = centerY + (radius - 60) * sin(angle).toFloat() + 12
            canvas.drawText(label, xText, yText, textPaint)
        }

        // 3. 绘制指针
        val pointerAngle = Math.toRadians(startAngle + sweepAngle * currentValue / maxValue.toDouble())
        val pointerLength = radius - 80
        val xPointer = centerX + pointerLength * cos(pointerAngle).toFloat()
        val yPointer = centerY + pointerLength * sin(pointerAngle).toFloat()
        canvas.drawLine(centerX, centerY, xPointer, yPointer, pointerPaint)

        // 4. 中心圆点
        canvas.drawCircle(centerX, centerY, 10f, pointerPaint)
    }
}

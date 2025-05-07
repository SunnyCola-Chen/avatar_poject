package com.example.myapplication.avatar.ui

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Shader
import android.graphics.SweepGradient
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator

class AvatarRingView(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 20f
        strokeCap = Paint.Cap.ROUND
    }

    private val colors = intArrayOf(
        Color.TRANSPARENT,
        Color.argb(150, 0, 200, 255), // 半透明蓝绿色
        Color.argb(50, 0, 200, 255),
        Color.TRANSPARENT
    )

    private val shader: Shader by lazy {
        SweepGradient(
            width / 2f, height / 2f,
            colors,
            floatArrayOf(0f, 0.3f, 0.7f, 1f)) // 颜色分布位置
    }

    private var ringRadius = 0f
    private var animator: ValueAnimator? = null

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        ringRadius = (minOf(w, h) / 2f - 40f )// 光圈半径（留出边距）
        startAnimation()
    }

    override fun onDraw(canvas: Canvas) {
        paint.shader = shader
        canvas.drawCircle(width / 2f, height / 2f, ringRadius, paint)
    }

    private fun startAnimation() {
        animator?.cancel()
        animator = ValueAnimator.ofFloat(0.8f, 1.2f).apply {
            duration = 1500
            interpolator = LinearInterpolator()
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.REVERSE
            addUpdateListener { animator ->
                paint.strokeWidth = 20f * (animator.animatedValue as Float)
                invalidate() // 触发重绘
            }
            start()
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        animator?.cancel() // 避免内存泄漏
    }
}

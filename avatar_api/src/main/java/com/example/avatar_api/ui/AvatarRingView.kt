package com.example.avatar_api.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Shader
import android.view.View
import com.example.avatar_api.model.AvatarRingUIData

class AvatarRingView(context: Context, val config: AvatarRingUIData): View(context) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    val gradient: LinearGradient
        get() = LinearGradient(
            0f, 0f,
            width.toFloat(), height.toFloat(),
            intArrayOf(123, 234),
            null,
            Shader.TileMode.CLAMP
        )


    override fun onDraw(canvas: Canvas) {
        val centerX = canvas.width / 2f
        val centerY = canvas.height / 2f
        val radius = minOf(canvas.width, canvas.height) / 2f

        paint.shader = gradient
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 20F

        // 绘制光环
        canvas.drawCircle(centerX, centerY, radius - config.outline.invoke(54), paint)
    }
}
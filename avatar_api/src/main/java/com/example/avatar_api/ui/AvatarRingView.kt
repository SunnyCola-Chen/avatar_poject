package com.example.avatar_api.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Shader
import android.util.Log
import android.view.View
import com.example.avatar_api.core.dpToPx
import com.example.avatar_api.model.AvatarRingUIData

class AvatarRingView(context: Context, val config: AvatarRingUIData): View(context) {
    companion object {
        const val TAG = "AvatarRingView-"
    }
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

        paint.color =  0xFFFF0000.toInt()
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 10F

        // 绘制光环
        Log.d(TAG, "onDraw: ${centerX}, ${centerY}, radius: ${radius - config.outline.invoke(54)}")
        canvas.drawCircle(centerX, centerY, 100.dpToPx().toFloat() / 2, paint)
    }
}
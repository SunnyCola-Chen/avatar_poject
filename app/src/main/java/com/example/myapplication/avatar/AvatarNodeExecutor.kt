package com.example.myapplication.avatar

import android.content.Context
import android.graphics.Canvas
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Shader
import android.view.View
import android.widget.RelativeLayout
import com.example.myapplication.avatar.model.AvatarBadgeUIData
import com.example.myapplication.avatar.model.AvatarNodeType
import com.example.myapplication.avatar.model.AvatarRingUIData
import com.example.myapplication.avatar.model.AvatarUIData
import com.example.myapplication.avatar.ui.CircleImageView

class AvatarNodeExecutor {

    fun init(containerView: AvatarComponent): CircleImageView {
        val avatarImageView = initImageView(containerView.context)
        val lp = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT).apply {
            addRule(RelativeLayout.CENTER_IN_PARENT)
        }
        containerView.addView(avatarImageView, lp)
        return avatarImageView
    }

    private fun initImageView(ctx: Context): CircleImageView {
        return CircleImageView(ctx)
    }

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    fun executeRingNode(canvas: Canvas, config: AvatarRingUIData) {
        val centerX = canvas.width / 2f
        val centerY = canvas.height / 2f
        val radius = minOf(canvas.width, canvas.height) / 2f
        
        // 创建渐变
        val gradient = LinearGradient(
            0f, 0f,
            canvas.width.toFloat(), canvas.height.toFloat(),
            intArrayOf(123, 234),
            null,
            Shader.TileMode.CLAMP
        )
        
        paint.shader = gradient
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 20F
        
        // 绘制光环
        canvas.drawCircle(centerX, centerY, radius - config.outline.invoke(54), paint)
    }

    fun executeBadgeNode(canvas: Canvas, config: AvatarBadgeUIData) {
        val centerX = canvas.width / 2f
        val centerY = canvas.height / 2f
        val radius = minOf(canvas.width, canvas.height) / 2f
        
        // 设置徽章颜色
        paint.color = 123
        paint.style = Paint.Style.FILL
        
        // 计算徽章位置（右上角）
        val badgeRadius = radius * 0.2f
        val badgeX = centerX + radius * 0.7f
        val badgeY = centerY - radius * 0.7f
        
        // 绘制徽章
        canvas.drawCircle(badgeX, badgeY, badgeRadius, paint)
    }

    fun execute(avatarData: MutableMap<AvatarNodeType, AvatarUIData>?) {
        avatarData ?: return
        for (avatarNode in avatarData) {
            when (avatarNode.key) {
//                AvatarNodeType.RING -> executeRingNode(config = avatarNode.value as? AvatarRingUIData)
//                AvatarNodeType.BADGE -> executeBadgeNode(config = avatarNode.value as? AvatarBadgeUIData)
                else -> return
            }
        }
    }
} 
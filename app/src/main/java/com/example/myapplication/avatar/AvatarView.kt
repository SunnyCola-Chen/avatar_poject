package com.example.myapplication.avatar

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.bumptech.glide.Glide
import com.example.myapplication.avatar.model.AvatarBadgeUIData
import com.example.myapplication.avatar.model.AvatarNodeType
import com.example.myapplication.avatar.model.AvatarRingUIData
import com.example.myapplication.avatar.model.AvatarUIData

class AvatarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var viewModel: AvatarViewModel? = null
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val nodeExecutor = AvatarNodeExecutor()
    private var avatarBitmap: android.graphics.Bitmap? = null

    fun setViewModel(viewModel: AvatarViewModel, lifecycleOwner: LifecycleOwner) {
        this.viewModel = viewModel
        viewModel.avatarLiveData.observe(lifecycleOwner) { nodeMap ->
            invalidate() // 当数据变化时重绘
        }
        
        // 加载默认头像
        loadDefaultAvatar()
    }

    private fun loadDefaultAvatar() {
        Glide.with(context)
            .asBitmap()
            .load("https://picsum.photos/200")
            .into(object : com.bumptech.glide.request.target.SimpleTarget<android.graphics.Bitmap>() {
                override fun onResourceReady(
                    resource: android.graphics.Bitmap,
                    transition: com.bumptech.glide.request.transition.Transition<in android.graphics.Bitmap>?
                ) {
                    avatarBitmap = resource
                    invalidate()
                }
            })
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        
        val nodeMap = viewModel?.avatarLiveData?.value ?: return
        
        // 绘制基础头像
        drawBaseAvatar(canvas)
        
        // 绘制光环
        nodeMap[AvatarNodeType.RING]?.let { ringData ->
            nodeExecutor.executeRingNode(canvas, ringData as AvatarRingUIData)
        }
        
        // 绘制徽章
        nodeMap[AvatarNodeType.BADGE]?.let { badgeData ->
            nodeExecutor.executeBadgeNode(canvas, badgeData as AvatarBadgeUIData)
        }
    }

    private fun drawBaseAvatar(canvas: Canvas) {
        avatarBitmap?.let { bitmap ->
            val centerX = width / 2f
            val centerY = height / 2f
            val radius = minOf(width, height) / 2f
            
            // 创建圆形裁剪区域
            canvas.save()
            canvas.clipPath(android.graphics.Path().apply {
                addCircle(centerX, centerY, radius, android.graphics.Path.Direction.CW)
            })
            
            // 绘制头像
            canvas.drawBitmap(
                bitmap,
                null,
                android.graphics.RectF(
                    centerX - radius,
                    centerY - radius,
                    centerX + radius,
                    centerY + radius
                ),
                paint
            )
            
            canvas.restore()
        }
    }
} 
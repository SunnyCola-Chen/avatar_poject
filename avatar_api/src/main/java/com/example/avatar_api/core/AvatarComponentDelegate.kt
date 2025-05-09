package com.example.avatar_api.core

import android.util.Log
import android.widget.RelativeLayout
import android.widget.RelativeLayout.LayoutParams
import com.example.avatar_api.model.AvatarViewModel
import com.example.avatar_api.ui.CircleImageView
import src.main.java.com.example.avatar_api.AvatarComponentView
import androidx.core.net.toUri
import com.example.avatar_api.R

class AvatarComponentDelegate(val container: AvatarComponentView, val config: AvatarComponentConfig)  {
    companion object {
        const val TAG = "AvatarComponentDelegate-"
    }
    private lateinit var avatarNodeExecutor: AvatarNodeExecutor
    private lateinit var avatarImageView: CircleImageView
    private lateinit var avatarController: AvatarController
    private val avatarViewModel: AvatarViewModel by lazy { AvatarViewModel() }

    fun buildAvatar() {
        // 增加头像图片
        avatarImageView = CircleImageView(container.context).apply {
            id = R.id.avatar
            layoutParams = LayoutParams(config.avatarConfig.avatarSize, config.avatarConfig.avatarSize).apply {
                addRule(RelativeLayout.CENTER_IN_PARENT)
            }
            setOnClickListener { config.avatarConfig.defaultClickListener?.invoke() }
        }
        container.addView(avatarImageView)

        // 初始化node渲染器
        avatarNodeExecutor = AvatarNodeExecutor(container, avatarImageView, config.avatarConfig.avatarSize)
        avatarController = AvatarController(config.businesses).apply {
            avatarViewModel = this@AvatarComponentDelegate.avatarViewModel
        }

        // 观察头像数据变化
        Log.d(TAG, "buildAvatar: ${config.avatarConfig.lifecycleOwner}")
        config.avatarConfig.lifecycleOwner?.let {
            avatarViewModel.observe(it) {
                avatarNodeExecutor.updateAvatarNode(it)
            }
        }
    }

    fun onBind(data: Any) {
        avatarImageView.setImageURI("https://picsum.photos/300/200".toUri())
//        avatarImageView.controller = Fresco.newDraweeControllerBuilder()
//            .setUri(url.toUri())
//            .setAutoPlayAnimations(true)
//            .build()

        avatarController.updateState(data = data)
    }

}
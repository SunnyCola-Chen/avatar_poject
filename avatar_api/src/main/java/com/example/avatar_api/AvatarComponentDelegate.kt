package com.example.avatar_api

import android.widget.RelativeLayout
import android.widget.RelativeLayout.LayoutParams
import com.example.avatar_api.model.AvatarViewModel
import com.example.avatar_api.ui.CircleImageView
import src.main.java.com.example.avatar_api.AvatarComponentView

class AvatarComponentDelegate(val container: AvatarComponentView, val config: AvatarComponentConfig)  {
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
        avatarNodeExecutor = AvatarNodeExecutor(container, avatarImageView)
        avatarController = AvatarController(config.businesses).apply {
            avatarViewModel = this@AvatarComponentDelegate.avatarViewModel
        }

        // 观察头像数据变化
        config.avatarConfig.lifecycleOwner?.let {
            avatarViewModel.observe(it) {
                avatarNodeExecutor.updateAvatarNode(it)
            }
        }
    }

    fun onBind(data: Any) {
        avatarImageView.setImageUrl("")
        avatarController.updateState(data = data)
    }

}
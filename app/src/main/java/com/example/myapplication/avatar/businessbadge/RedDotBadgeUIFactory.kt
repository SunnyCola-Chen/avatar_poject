package com.example.myapplication.avatar.businessbadge

import android.graphics.Color
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.example.avatar_api.core.dpToPx
import com.example.avatar_api.interfac.IAvatarUINodeFactory
import com.example.avatar_api.model.AvatarBadgeUIData
import com.example.avatar_api.model.AvatarNodePosition
import com.example.avatar_api.model.AvatarRingUIData
import com.example.myapplication.R

class RedDotBadgeUIFactory(val variant: AvatarRedDotBadgeVariant?) :
    IAvatarUINodeFactory<AvatarRedDotBadgeVariant, AvatarRedDotBadgeState> {
    override fun getBadgeConfig(state: AvatarRedDotBadgeState): AvatarBadgeUIData? {
        return AvatarBadgeUIData(
            view = ImageView(variant?.ctx).apply {
                layoutParams = ViewGroup.LayoutParams(20.dpToPx(), 20.dpToPx()) // 20dp大小
                background = ContextCompat.getDrawable(context, R.drawable.ic_launcher_background)
            },
            position = AvatarNodePosition(
                alignAvatar = false,
                marginEnd = { 0 },
                marginBottom = { 0 }
            ),
            size = { 30 },
            color = Color.RED
        )
    }

    override fun getRingConfig(state: AvatarRedDotBadgeState): AvatarRingUIData? {
        return null
    }
} 
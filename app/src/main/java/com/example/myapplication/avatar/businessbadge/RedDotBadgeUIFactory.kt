package com.example.myapplication.avatar.businessbadge

import android.graphics.Color
import com.example.avatar_api.interfac.IAvatarUINodeFactory
import com.example.avatar_api.model.AvatarBadgeUIData
import com.example.avatar_api.model.AvatarNodePosition
import com.example.avatar_api.model.AvatarRingUIData

class RedDotBadgeUIFactory(val variant: AvatarRedDotBadgeVariant) :
    IAvatarUINodeFactory<AvatarRedDotBadgeVariant, AvatarRedDotBadgeState> {
    override fun getBadgeConfig(state: AvatarRedDotBadgeState): AvatarBadgeUIData? {
        if (!state.isVisible) return null
        
        return AvatarBadgeUIData(
            position = AvatarNodePosition(
                alignAvatar = true,
                marginEnd = { 0 },
                marginBottom = { 0 }
            ),
            size = { 12 },
            color = Color.RED
        )
    }

    override fun getRingConfig(state: AvatarRedDotBadgeState): AvatarRingUIData? {
        return null
    }
} 
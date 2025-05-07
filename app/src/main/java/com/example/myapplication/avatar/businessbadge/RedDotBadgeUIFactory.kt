package com.example.myapplication.avatar.businessbadge

import androidx.compose.ui.graphics.Color
import com.example.myapplication.avatar.IAvatarUINodeFactory
import com.example.myapplication.avatar.model.AvatarBadgeUIData
import com.example.myapplication.avatar.model.AvatarNodePosition
import com.example.myapplication.avatar.model.AvatarRedDotBadgeState
import com.example.myapplication.avatar.model.AvatarRedDotBadgeVariant
import com.example.myapplication.avatar.model.AvatarRingUIData

class RedDotBadgeUIFactory : IAvatarUINodeFactory<AvatarRedDotBadgeVariant, AvatarRedDotBadgeState> {
    override fun getBadgeConfig(
        variantType: AvatarRedDotBadgeVariant,
        state: AvatarRedDotBadgeState
    ): AvatarBadgeUIData? {
        if (!state.isVisible) return null
        
        return AvatarBadgeUIData(
            position = AvatarNodePosition(
                alignAvatar = true,
                marginEnd = { 0 },
                marginBottom = { 0 }
            ),
            size = { 12 },
            color = Color.Red
        )
    }

    override fun getRingConfig(
        variantType: AvatarRedDotBadgeVariant,
        state: AvatarRedDotBadgeState
    ): AvatarRingUIData? {
        return null
    }
} 
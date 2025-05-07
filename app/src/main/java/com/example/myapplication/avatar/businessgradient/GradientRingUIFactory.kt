package com.example.myapplication.avatar.businessgradient

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.example.myapplication.avatar.IAvatarUINodeFactory
import com.example.myapplication.avatar.model.AvatarBadgeUIData
import com.example.myapplication.avatar.model.AvatarGradientRingState
import com.example.myapplication.avatar.model.AvatarGradientRingVariant
import com.example.myapplication.avatar.model.AvatarRingUIData

class GradientRingUIFactory : IAvatarUINodeFactory<AvatarGradientRingVariant, AvatarGradientRingState> {
    override fun getRingConfig(
        variantType: AvatarGradientRingVariant,
        state: AvatarGradientRingState
    ): AvatarRingUIData {
        return AvatarRingUIData(
            outline = { avatarSize -> (avatarSize * 0.05).toInt() },  // 5% 的间距
            width = { avatarSize -> (avatarSize * 0.08).toInt() },    // 8% 的宽度
            color = state.startColor,
            gradient = Brush.linearGradient(
                colors = listOf(state.startColor, state.endColor)
            )
        )
    }

    override fun getBadgeConfig(
        variantType: AvatarGradientRingVariant,
        state: AvatarGradientRingState
    ): AvatarBadgeUIData? = null
} 
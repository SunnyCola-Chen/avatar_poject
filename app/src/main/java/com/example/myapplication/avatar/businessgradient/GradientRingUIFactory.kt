package com.example.myapplication.avatar.businessgradient

import com.example.avatar_api.interfac.IAvatarUINodeFactory
import com.example.avatar_api.model.AvatarBadgeUIData
import com.example.avatar_api.model.AvatarRingUIData

class GradientRingUIFactory(val variant: AvatarGradientRingVariant?) :
    IAvatarUINodeFactory<AvatarGradientRingVariant, AvatarGradientRingState> {

    override fun getRingConfig(state: AvatarGradientRingState): AvatarRingUIData? {
        return AvatarRingUIData(
            outline = { avatarSize -> (avatarSize * 0.05).toInt() },  // 5% 的间距
            strokeWidth = { avatarSize -> (avatarSize * 0.08).toInt() },    // 8% 的宽度
            color = state.startColor,
        )
    }

    override fun getBadgeConfig(state: AvatarGradientRingState): AvatarBadgeUIData? {
        return null
    }
} 
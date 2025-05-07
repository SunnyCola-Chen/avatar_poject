package com.example.myapplication.avatar.businesssample

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myapplication.avatar.IAvatarUINodeFactory
import com.example.myapplication.avatar.model.AvatarBusinessSampleState
import com.example.myapplication.avatar.model.AvatarBusinessSampleVariant
import com.example.myapplication.avatar.model.AvatarRingUIData
import com.example.myapplication.avatar.model.AvatarBadgeUIData

class AvatarBusinessSampleUIFactory: IAvatarUINodeFactory<AvatarBusinessSampleVariant, AvatarBusinessSampleState> {
    override fun getRingConfig(
        variantType: AvatarBusinessSampleVariant,
        state: AvatarBusinessSampleState
    ): AvatarRingUIData {
        return AvatarRingUIData(
            outline = { avatarSize -> (avatarSize * 0.05).toInt() },  // 5% 的间距
            width = { avatarSize -> (avatarSize * 0.08).toInt() },    // 8% 的宽度
            color = Color.Red
        )
    }

    override fun getBadgeConfig(
        variantType: AvatarBusinessSampleVariant,
        state: AvatarBusinessSampleState
    ): AvatarBadgeUIData? = null
}
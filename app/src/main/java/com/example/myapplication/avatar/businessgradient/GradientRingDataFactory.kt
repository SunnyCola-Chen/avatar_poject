package com.example.myapplication.avatar.businessgradient

import com.example.avatar_api.core.BusinessData
import com.example.avatar_api.interfac.IAvatarDataFactory

class GradientRingDataFactory(val variant: AvatarGradientRingVariant?) :
    IAvatarDataFactory<AvatarGradientRingVariant, AvatarGradientRingState> {
    override fun getAvatarState(data: Any?): AvatarGradientRingState {
        return AvatarGradientRingState()
    }
} 
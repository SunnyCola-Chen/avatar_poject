package com.example.myapplication.avatar.businessgradient

import com.example.myapplication.avatar.IAvatarDataFactory
import com.example.myapplication.avatar.model.AvatarGradientRingState
import com.example.myapplication.avatar.model.AvatarGradientRingVariant

class GradientRingDataFactory : IAvatarDataFactory<AvatarGradientRingVariant, AvatarGradientRingState> {
    override fun getAvatarState(variantType: AvatarGradientRingVariant): AvatarGradientRingState {
        return AvatarGradientRingState()
    }
} 
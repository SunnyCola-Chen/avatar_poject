package com.example.myapplication.avatar.businessgradient

import com.example.myapplication.avatar.IAvatarBusinessConfig
import com.example.myapplication.avatar.model.AvatarBusinessType
import com.example.myapplication.avatar.model.AvatarGradientRingState
import com.example.myapplication.avatar.model.AvatarGradientRingVariant

class GradientRingBusinessConfig : IAvatarBusinessConfig<AvatarGradientRingVariant, AvatarGradientRingState> {
    override fun getAvatarBusinessType(): AvatarBusinessType {
        return AvatarBusinessType.BUSINESS_RING
    }

    override fun getAvatarUINodeFactory(variantType: AvatarGradientRingVariant): IAvatarUINodeFactory<AvatarGradientRingVariant, AvatarGradientRingState> {
        return GradientRingUIFactory()
    }

    override fun getAvatarDataFactory(variantType: AvatarGradientRingVariant): IAvatarDataFactory<AvatarGradientRingVariant, AvatarGradientRingState> {
        return GradientRingDataFactory()
    }
} 
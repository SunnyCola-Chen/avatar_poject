package com.example.myapplication.avatar.businessgradient

import com.example.avatar_api.interfac.IAvatarBusinessConfig
import com.example.avatar_api.interfac.IAvatarDataFactory
import com.example.avatar_api.interfac.IAvatarUINodeFactory
import com.example.avatar_api.model.AvatarBusinessType

class GradientRingBusinessConfig : IAvatarBusinessConfig<AvatarGradientRingVariant, AvatarGradientRingState> {
    override fun getBusinessType(): AvatarBusinessType {
        return AvatarBusinessType.BUSINESS_RING
    }

    override fun getUIFactory(variant: AvatarGradientRingVariant): IAvatarUINodeFactory<AvatarGradientRingVariant, AvatarGradientRingState> {
        return GradientRingUIFactory(variant)
    }

    override fun getDataFactory(variant: AvatarGradientRingVariant): IAvatarDataFactory<AvatarGradientRingVariant, AvatarGradientRingState> {
        return GradientRingDataFactory(variant)
    }
} 
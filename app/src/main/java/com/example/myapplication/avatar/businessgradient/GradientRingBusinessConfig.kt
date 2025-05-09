package com.example.myapplication.avatar.businessgradient

import com.example.avatar_api.interfac.IAvatarBusinessConfig
import com.example.avatar_api.interfac.IAvatarDataFactory
import com.example.avatar_api.interfac.IAvatarUINodeFactory
import com.example.avatar_api.model.AvatarBusinessType
import com.example.avatar_api.model.AvatarVariant

class GradientRingBusinessConfig : IAvatarBusinessConfig<AvatarGradientRingVariant, AvatarGradientRingState> {
    override fun getBusinessType(): AvatarBusinessType {
        return AvatarBusinessType.BUSINESS_RING
    }

    override fun getUIFactory(variant: AvatarVariant): IAvatarUINodeFactory<AvatarGradientRingVariant, AvatarGradientRingState> {
        return GradientRingUIFactory(variant as? AvatarGradientRingVariant)
    }

    override fun getDataFactory(variant: AvatarVariant): IAvatarDataFactory<AvatarGradientRingVariant, AvatarGradientRingState> {
        return GradientRingDataFactory(variant as? AvatarGradientRingVariant)
    }
} 
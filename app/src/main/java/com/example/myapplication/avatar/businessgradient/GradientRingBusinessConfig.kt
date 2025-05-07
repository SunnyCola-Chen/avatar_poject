package com.example.myapplication.avatar.businessgradient

import com.example.avatar_api.interfac.IAvatarBusinessConfig
import com.example.myapplication.avatar.model.AvatarGradientRingState
import com.example.myapplication.avatar.model.AvatarGradientRingVariant

class GradientRingBusinessConfig :
    com.example.avatar_api.interfac.IAvatarBusinessConfig<AvatarGradientRingVariant, AvatarGradientRingState> {
    override fun getAvatarBusinessType(): com.example.avatar_api.model.AvatarBusinessType {
        return com.example.avatar_api.model.AvatarBusinessType.BUSINESS_RING
    }

    override fun getAvatarUINodeFactory(variantType: AvatarGradientRingVariant): IAvatarUINodeFactory<AvatarGradientRingVariant, AvatarGradientRingState> {
        return GradientRingUIFactory()
    }

    override fun getAvatarDataFactory(variantType: AvatarGradientRingVariant): IAvatarDataFactory<AvatarGradientRingVariant, AvatarGradientRingState> {
        return GradientRingDataFactory()
    }
} 
package com.example.myapplication.avatar.businesssample

import com.example.avatar_api.interfac.IAvatarBusinessConfig
import com.example.myapplication.avatar.IAvatarDataFactory
import com.example.myapplication.avatar.IAvatarUINodeFactory
import com.example.myapplication.avatar.model.AvatarBusinessSampleState
import com.example.myapplication.avatar.model.AvatarBusinessSampleVariant

class AvatarBusinessSampleConfig:
    com.example.avatar_api.interfac.IAvatarBusinessConfig<AvatarBusinessSampleVariant, AvatarBusinessSampleState> {
    override fun getAvatarBusinessType(): com.example.avatar_api.model.AvatarBusinessType {
        return com.example.avatar_api.model.AvatarBusinessType.BUSINESS_RING
    }

    override fun getAvatarUINodeFactory(variantType: AvatarBusinessSampleVariant): IAvatarUINodeFactory<AvatarBusinessSampleVariant, AvatarBusinessSampleState> {
        return AvatarBusinessSampleUIFactory()
    }

    override fun getAvatarDataFactory(variantType: AvatarBusinessSampleVariant): IAvatarDataFactory<AvatarBusinessSampleVariant, AvatarBusinessSampleState> {
        return AvatarBusinessSampleDataFactory()
    }


}
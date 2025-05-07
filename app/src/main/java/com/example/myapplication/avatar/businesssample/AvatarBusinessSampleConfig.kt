package com.example.myapplication.avatar.businesssample

import com.example.myapplication.avatar.IAvatarBusinessConfig
import com.example.myapplication.avatar.IAvatarDataFactory
import com.example.myapplication.avatar.IAvatarUINodeFactory
import com.example.myapplication.avatar.model.AvatarBusinessSampleState
import com.example.myapplication.avatar.model.AvatarBusinessSampleVariant
import com.example.myapplication.avatar.model.AvatarBusinessType

class AvatarBusinessSampleConfig: IAvatarBusinessConfig<AvatarBusinessSampleVariant, AvatarBusinessSampleState> {
    override fun getAvatarBusinessType(): AvatarBusinessType {
        return AvatarBusinessType.BUSINESS_RING
    }

    override fun getAvatarUINodeFactory(variantType: AvatarBusinessSampleVariant): IAvatarUINodeFactory<AvatarBusinessSampleVariant, AvatarBusinessSampleState> {
        return AvatarBusinessSampleUIFactory()
    }

    override fun getAvatarDataFactory(variantType: AvatarBusinessSampleVariant): IAvatarDataFactory<AvatarBusinessSampleVariant, AvatarBusinessSampleState> {
        return AvatarBusinessSampleDataFactory()
    }


}
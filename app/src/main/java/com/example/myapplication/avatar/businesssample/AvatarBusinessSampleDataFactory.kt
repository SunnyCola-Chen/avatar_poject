package com.example.myapplication.avatar.businesssample

import com.example.myapplication.avatar.IAvatarDataFactory
import com.example.myapplication.avatar.model.AvatarBusinessSampleState
import com.example.myapplication.avatar.model.AvatarBusinessSampleVariant
import com.example.myapplication.avatar.model.AvatarDataState

class AvatarBusinessSampleDataFactory: IAvatarDataFactory<AvatarBusinessSampleVariant, AvatarBusinessSampleState> {
    override fun getAvatarState(variantType: AvatarBusinessSampleVariant): AvatarBusinessSampleState {
        return AvatarBusinessSampleState()
    }

}
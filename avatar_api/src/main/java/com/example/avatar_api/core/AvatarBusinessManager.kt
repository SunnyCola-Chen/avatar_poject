package com.example.avatar_api.core

import com.example.avatar_api.interfac.IAvatarBusinessConfig
import com.example.avatar_api.model.AvatarBusinessType
import com.example.avatar_api.model.AvatarDataState
import com.example.avatar_api.model.AvatarVariant
import com.example.avatar_api.service.IAvatarBusinessService

object AvatarBusinessManager : IAvatarBusinessService {
    val businessMap = mutableMapOf<AvatarBusinessType, IAvatarBusinessConfig<AvatarVariant, AvatarDataState>>()

    override fun registerBusiness(
        type: AvatarBusinessType,
        factory: IAvatarBusinessConfig<AvatarVariant, AvatarDataState>
    ) {
        businessMap[type] = factory
    }

    fun getBusinessConfig(type: AvatarBusinessType): IAvatarBusinessConfig<out AvatarVariant, out AvatarDataState>? {
        return businessMap[type]
    }
}
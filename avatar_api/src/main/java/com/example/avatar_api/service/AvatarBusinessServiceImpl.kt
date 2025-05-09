package com.example.avatar_api.service

import com.example.avatar_api.core.AvatarBusinessManager
import com.example.avatar_api.interfac.IAvatarBusinessConfig
import com.example.avatar_api.model.AvatarBusinessType
import com.example.avatar_api.model.AvatarDataState
import com.example.avatar_api.model.AvatarVariant

class AvatarBusinessServiceImpl: IAvatarBusinessService {
    override fun registerBusiness(
        type: AvatarBusinessType,
        factory: IAvatarBusinessConfig<AvatarVariant, AvatarDataState>
    ) {
        AvatarBusinessManager.registerBusiness(type, factory)
    }
} 
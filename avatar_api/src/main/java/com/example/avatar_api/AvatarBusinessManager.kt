package com.example.avatar_api

import com.example.avatar_api.interfac.IAvatarBusinessConfig
import com.example.avatar_api.model.AvatarBusinessType
import com.example.avatar_api.model.AvatarDataState
import com.example.avatar_api.model.AvatarNodeType
import com.example.avatar_api.model.AvatarVariant
import com.example.avatar_api.service.IAvatarBusinessService

object AvatarBusinessManager: IAvatarBusinessService {
    val businessMap: MutableMap<AvatarBusinessType, out IAvatarBusinessConfig<AvatarVariant, AvatarDataState>> = mutableMapOf()

    override fun registerBusiness(
        type: AvatarBusinessType,
        factory: IAvatarBusinessConfig<out AvatarVariant, out AvatarDataState>
    ) {

        TODO("Not yet implemented")
    }
}
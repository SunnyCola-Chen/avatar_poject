package com.example.avatar_api.service

import com.example.avatar_api.interfac.IAvatarBusinessConfig
import com.example.avatar_api.model.AvatarBusinessType
import com.example.avatar_api.model.AvatarDataState
import com.example.avatar_api.model.AvatarVariant
import java.util.ServiceLoader

interface IAvatarBusinessService {

    fun registerBusiness(type: AvatarBusinessType, factory: IAvatarBusinessConfig<out AvatarVariant, out AvatarDataState>)
}

object AvatarBusinessService : IAvatarBusinessService by (ServiceLoader.load(IAvatarBusinessService::class.java).firstOrNull() 
    ?: AvatarBusinessServiceImpl())
package com.example.avatar_api.interfac

import com.example.avatar_api.model.AvatarBusinessType
import com.example.avatar_api.model.AvatarDataState
import com.example.avatar_api.model.AvatarVariant

interface IAvatarBusinessConfig<out V : AvatarVariant, out S : AvatarDataState> {
    fun getBusinessType(): AvatarBusinessType
    fun getDataFactory(variant: AvatarVariant): IAvatarDataFactory<out V, out S>
    fun getUIFactory(variant: AvatarVariant): IAvatarUINodeFactory<out V, out S>
}
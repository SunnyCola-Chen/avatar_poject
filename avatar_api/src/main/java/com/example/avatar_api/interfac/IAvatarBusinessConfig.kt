package com.example.avatar_api.interfac

import com.example.avatar_api.model.AvatarBusinessType
import com.example.avatar_api.model.AvatarDataState
import com.example.avatar_api.model.AvatarVariant

interface IAvatarBusinessConfig<V : AvatarVariant, S : AvatarDataState> {
    fun getBusinessType(): AvatarBusinessType

    fun getDataFactory(variant: V): IAvatarDataFactory<V, S>
    fun getUIFactory(variant: V): IAvatarUINodeFactory<V, S>
}
package com.example.myapplication.avatar

import com.example.myapplication.avatar.model.AvatarBusinessType
import com.example.myapplication.avatar.model.AvatarDataState
import com.example.myapplication.avatar.model.AvatarVariant

interface IAvatarBusinessConfig<V : AvatarVariant, S : AvatarDataState> {
    fun getBusinessType(): AvatarBusinessType
    fun getVariant(): V
    fun getDataFactory(variant: V): IAvatarDataFactory<V, S>
    fun getUIFactory(): IAvatarUINodeFactory<V, S>
}
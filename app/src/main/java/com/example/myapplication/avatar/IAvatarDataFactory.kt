package com.example.myapplication.avatar

interface IAvatarDataFactory<AVATAR_VARIANT_TYPE, AVATAR_STATE> {

    fun getAvatarState(variantType: AVATAR_VARIANT_TYPE): AVATAR_STATE

}
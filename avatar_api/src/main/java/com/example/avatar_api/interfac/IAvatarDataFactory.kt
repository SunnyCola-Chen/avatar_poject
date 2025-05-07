package com.example.avatar_api.interfac

interface IAvatarDataFactory<AVATAR_VARIANT_TYPE, AVATAR_STATE> {

    fun getAvatarState(data: Any?): AVATAR_STATE

}
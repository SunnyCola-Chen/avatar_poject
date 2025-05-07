package com.example.myapplication.avatar

import com.example.myapplication.avatar.model.AvatarBadgeUIData
import com.example.myapplication.avatar.model.AvatarRingUIData

interface IAvatarUINodeFactory<AVATAR_VARIANT_TYPE, AVATAR_STATE> {

    fun getRingConfig(variantType: AVATAR_VARIANT_TYPE, state: AVATAR_STATE): AvatarRingUIData?
    fun getBadgeConfig(variantType: AVATAR_VARIANT_TYPE, state: AVATAR_STATE): AvatarBadgeUIData?
}
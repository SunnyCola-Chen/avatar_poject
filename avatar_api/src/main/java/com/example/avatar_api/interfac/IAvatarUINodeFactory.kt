package com.example.avatar_api.interfac

import com.example.avatar_api.model.AvatarBadgeUIData
import com.example.avatar_api.model.AvatarDataState
import com.example.avatar_api.model.AvatarRingUIData
import com.example.avatar_api.model.AvatarVariant

interface IAvatarUINodeFactory<AVATAR_VARIANT, AVATAR_STATE: AvatarDataState> {
    fun getRingConfig(state: AVATAR_STATE): AvatarRingUIData?
    fun getBadgeConfig(state: AVATAR_STATE): AvatarBadgeUIData?
}
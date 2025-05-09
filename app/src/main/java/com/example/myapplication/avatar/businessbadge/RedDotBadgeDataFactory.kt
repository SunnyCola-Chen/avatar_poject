package com.example.myapplication.avatar.businessbadge

import com.example.avatar_api.interfac.IAvatarDataFactory

class RedDotBadgeDataFactory(val variant: AvatarRedDotBadgeVariant?) :
    IAvatarDataFactory<AvatarRedDotBadgeVariant, AvatarRedDotBadgeState> {
    override fun getAvatarState(data: Any?): AvatarRedDotBadgeState {
        return AvatarRedDotBadgeState()
    }
} 
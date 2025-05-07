package com.example.myapplication.avatar.businessbadge

import com.example.myapplication.avatar.IAvatarDataFactory
import com.example.myapplication.avatar.model.AvatarRedDotBadgeState
import com.example.myapplication.avatar.model.AvatarRedDotBadgeVariant

class RedDotBadgeDataFactory : IAvatarDataFactory<AvatarRedDotBadgeVariant, AvatarRedDotBadgeState> {
    override fun getAvatarState(variantType: AvatarRedDotBadgeVariant): AvatarRedDotBadgeState {
        return AvatarRedDotBadgeState()
    }
} 
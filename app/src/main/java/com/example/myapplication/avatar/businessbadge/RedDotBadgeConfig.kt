package com.example.myapplication.avatar.businessbadge

import com.example.myapplication.avatar.IAvatarBusinessConfig
import com.example.myapplication.avatar.model.AvatarBusinessType
import com.example.myapplication.avatar.model.AvatarRedDotBadgeState
import com.example.myapplication.avatar.model.AvatarRedDotBadgeVariant

class RedDotBadgeConfig : IAvatarBusinessConfig<AvatarRedDotBadgeVariant, AvatarRedDotBadgeState> {
    override fun getAvatarBusinessType(): AvatarBusinessType {
        return AvatarBusinessType.BUSINESS_BADGE
    }

    override fun getAvatarUINodeFactory(variantType: AvatarRedDotBadgeVariant): IAvatarUINodeFactory<AvatarRedDotBadgeVariant, AvatarRedDotBadgeState> {
        return RedDotBadgeUIFactory()
    }

    override fun getAvatarDataFactory(variantType: AvatarRedDotBadgeVariant): IAvatarDataFactory<AvatarRedDotBadgeVariant, AvatarRedDotBadgeState> {
        return RedDotBadgeDataFactory()
    }
} 
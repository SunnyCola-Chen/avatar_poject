package com.example.myapplication.avatar.businessbadge

import com.example.avatar_api.interfac.IAvatarDataFactory
import com.example.avatar_api.interfac.IAvatarUINodeFactory
import com.example.avatar_api.model.AvatarBusinessType

class RedDotBadgeConfig :
    com.example.avatar_api.interfac.IAvatarBusinessConfig<AvatarRedDotBadgeVariant, AvatarRedDotBadgeState> {
    override fun getBusinessType(): AvatarBusinessType {
        return AvatarBusinessType.BUSINESS_BADGE
    }

    override fun getUIFactory(variant: AvatarRedDotBadgeVariant): IAvatarUINodeFactory<AvatarRedDotBadgeVariant, AvatarRedDotBadgeState> {
        return RedDotBadgeUIFactory(variant)
    }

    override fun getDataFactory(variant: AvatarRedDotBadgeVariant): IAvatarDataFactory<AvatarRedDotBadgeVariant, AvatarRedDotBadgeState> {
        return RedDotBadgeDataFactory(variant)
    }

} 
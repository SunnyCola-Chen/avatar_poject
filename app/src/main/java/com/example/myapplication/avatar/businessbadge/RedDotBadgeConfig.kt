package com.example.myapplication.avatar.businessbadge

import com.example.avatar_api.interfac.IAvatarDataFactory
import com.example.avatar_api.interfac.IAvatarUINodeFactory
import com.example.avatar_api.model.AvatarBusinessType
import com.example.avatar_api.model.AvatarVariant

class RedDotBadgeConfig :
    com.example.avatar_api.interfac.IAvatarBusinessConfig<AvatarRedDotBadgeVariant, AvatarRedDotBadgeState> {
    override fun getBusinessType(): AvatarBusinessType {
        return AvatarBusinessType.BUSINESS_BADGE
    }

    override fun getUIFactory(variant: AvatarVariant): IAvatarUINodeFactory<AvatarRedDotBadgeVariant, AvatarRedDotBadgeState> {
        return RedDotBadgeUIFactory(variant as? AvatarRedDotBadgeVariant)
    }

    override fun getDataFactory(variant: AvatarVariant): IAvatarDataFactory<AvatarRedDotBadgeVariant, AvatarRedDotBadgeState> {
        return RedDotBadgeDataFactory(variant as? AvatarRedDotBadgeVariant)
    }

} 
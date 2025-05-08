package com.example.avatar_api

import com.example.avatar_api.interfac.IAvatarBusinessConfig
import com.example.avatar_api.model.AvatarBusinessData
import com.example.avatar_api.model.AvatarBusinessType
import com.example.avatar_api.model.AvatarDataState
import com.example.avatar_api.model.AvatarNodeType
import com.example.avatar_api.model.AvatarUIData
import com.example.avatar_api.model.AvatarVariant
import com.example.avatar_api.model.AvatarViewModel

class AvatarController(val businessRegisterList: List<AvatarBusinessData>) {
    private val nodeConfigMap = mutableMapOf<AvatarNodeType, AvatarUIData>()
    private val candidateBusiness = mutableMapOf<AvatarNodeType, AvatarBusinessType>()
    private val nodeList = listOf(AvatarNodeType.RING, AvatarNodeType.BADGE)
    var avatarViewModel: AvatarViewModel? = null

    fun updateState(businessType: AvatarBusinessType? = null, data: Any?) {
        val tmpNodeConfigMap = mutableMapOf<AvatarNodeType, AvatarUIData?>()
        businessRegisterList.forEach { business ->
            tmpNodeConfigMap.clear()
            val businessConfig = AvatarBusinessManager.businessMap.get(business.businessType) as? IAvatarBusinessConfig<AvatarVariant, AvatarDataState>
            val state = businessConfig?.getDataFactory(business.variant)?.getAvatarState(data) ?: return@forEach
            val uiFactory = businessConfig?.getUIFactory(business.variant)

            tmpNodeConfigMap[AvatarNodeType.RING] = uiFactory?.getRingConfig(state)
            tmpNodeConfigMap[AvatarNodeType.BADGE] = uiFactory?.getBadgeConfig(state)

            for (nodeType in nodeList) {
                val avatarUIData = tmpNodeConfigMap.get(nodeType)
                if (business.businessType.priority < (candidateBusiness.get(nodeType)?.priority
                        ?: Int.MAX_VALUE) && avatarUIData != null
                ) {
                    // 更新
                    candidateBusiness[nodeType] = business.businessType
                    nodeConfigMap[nodeType] = avatarUIData
                }
            }
        }
        avatarViewModel?.updateNodeData(nodeConfigMap)
    }
}
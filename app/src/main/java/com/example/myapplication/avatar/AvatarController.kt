package com.example.myapplication.avatar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.avatar.businesssample.AvatarBusinessSampleConfig
import com.example.myapplication.avatar.model.AvatarBusinessType
import com.example.myapplication.avatar.model.AvatarNodeType
import com.example.myapplication.avatar.model.AvatarVariant
import com.example.myapplication.avatar.model.AvatarDataState
import com.example.myapplication.avatar.model.AvatarUIData

class AvatarController {
    private val businessConfigMap = mutableMapOf<AvatarBusinessType, IAvatarBusinessConfig<out AvatarVariant, out AvatarDataState>>()
    private val businessStateMap = mutableMapOf<AvatarBusinessType, AvatarDataState>()
    private val nodeConfigMap = mutableMapOf<AvatarNodeType, AvatarUIData>()
    private val variantMap = mutableMapOf<AvatarBusinessType, AvatarVariant>()
    private val _avatarLiveData = MutableLiveData<MutableMap<AvatarNodeType, AvatarUIData>>()
    val avatarLiveData: LiveData<MutableMap<AvatarNodeType, AvatarUIData>> = _avatarLiveData


    @Suppress("UNCHECKED_CAST")
    fun registerBusiness(config: IAvatarBusinessConfig<out AvatarVariant, out AvatarDataState>) {
        businessConfigMap[config.getBusinessType()] = config
    }

    fun updateState(businessType: AvatarBusinessType, state: AvatarDataState) {
        updateBusinessState(businessType, state)
        // 更新所有节点的配置
        updateNodeConfigs()
        _avatarLiveData.postValue(nodeConfigMap)
    }

    private fun updateBusinessState(businessType: AvatarBusinessType, state: AvatarDataState) {
        businessStateMap[businessType] = state
    }

    @Suppress("UNCHECKED_CAST")
    private fun updateNodeConfigs() {
        // 清空当前节点配置
        nodeConfigMap.clear()

        // 遍历所有业务配置，计算每个节点的配置
        businessConfigMap.forEach { (businessType, config) ->
            val state = businessStateMap[businessType] ?: return@forEach
            val variant = variantMap[businessType] ?: return@forEach
            val uiFactory = (config as IAvatarBusinessConfig<AvatarVariant, AvatarDataState>)
                .getUIFactory()

            // 计算并更新节点配置
//            when (businessType) {
//                AvatarBusinessType.BUSINESS_RING -> {
//                    val ringConfig = uiFactory.getRingConfig(variant, state) as? AvatarUIData
//                    if (ringConfig != null) {
//                        nodeConfigMap[AvatarNodeType.RING] = ringConfig
//                    }
//                }
//                AvatarBusinessType.BUSINESS_BADGE -> {
//                    val badgeConfig = (uiFactory as IAvatarUINodeFactory<AvatarVariant, AvatarDataState>)
//                        .getBadgeConfig(variant, state)
//                    if (badgeConfig != null) {
//                        nodeConfigMap[AvatarNodeType.BADGE] = badgeConfig
//                    }
//                }
//            }
        }
    }
}
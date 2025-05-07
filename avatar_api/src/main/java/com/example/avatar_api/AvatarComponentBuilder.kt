package com.example.avatar_api

import android.content.res.Resources.getSystem
import androidx.lifecycle.LifecycleOwner
import com.example.avatar_api.model.AvatarBusinessData

class AvatarComponentBuilder {
    private var avatarConfig: AvatarConfig? = null
    private val businesses = mutableListOf<BusinessData>()
    private var trackerConfig: TrackerConfig? = null

    fun defaultAvatarConfig(block: AvatarConfigBuilder.() -> Unit) {
        val builder = AvatarConfigBuilder().apply(block)
        avatarConfig = builder.build()
    }

    fun registerBusiness(vararg business: BusinessData) {
        businesses.addAll(business)
    }

    fun TrackerConfig(block: TrackerConfigBuilder.() -> Unit) {
        val builder = TrackerConfigBuilder().apply(block)
        trackerConfig = builder.build()
    }

    fun build(): AvatarComponentConfig {
        return AvatarComponentConfig(
            avatarConfig ?: throw IllegalStateException("AvatarConfig must be set"),
            businesses,
            trackerConfig
        )
    }
}

class AvatarConfigBuilder {
    var containerAvatarSize: Int = 96.dpToPx()
    var avatarSize: Int = 90.dpToPx()
    var defaultClickListener: (() -> Unit)? = null
    var lifecycleOwner: LifecycleOwner? = null

    fun build(): AvatarConfig {
        return AvatarConfig(containerAvatarSize, avatarSize, lifecycleOwner, defaultClickListener)
    }
}

class TrackerConfigBuilder {
    var enterFrom: String = ""
    var enterMethod: String = ""

    fun build(): TrackerConfig {
        return TrackerConfig(enterFrom, enterMethod)
    }
}

data class AvatarComponentConfig(
    val avatarConfig: AvatarConfig,
    val businesses: List<AvatarBusinessData>,
    val trackerConfig: TrackerConfig?
)

// AvatarConfig.kt
data class AvatarConfig(
    val containerSize: Int,       // 容器总大小
    val avatarSize: Int,          // 头像实际大小
    val lifecycleOwner: LifecycleOwner? = null,
    val defaultClickListener: (() -> Unit)? = null
)

data class TrackerConfig(
    val enterFrom: String,
    val enterMethod: String
)

sealed class BusinessData {
    data class Business1Data(val variant: Business1Variant) : BusinessData()
    data class Business2Data(val variant: Business2Variant) : BusinessData()
}

data class Business1Variant(val param: String = "")
data class Business2Variant(val flag: Boolean = false)

// 扩展函数：dp转px
fun Int.dpToPx(): Int = (this * getSystem().displayMetrics.density).toInt()
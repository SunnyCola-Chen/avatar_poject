package com.example.avatar_api.core

import android.content.res.Resources.getSystem
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import com.example.avatar_api.model.AvatarBusinessData

class AvatarComponentBuilder {
    companion object {
        const val TAG = "AvatarComponentBuilder-"
    }
    private var avatarConfig: AvatarConfig? = null
    private val businesses = mutableListOf<AvatarBusinessData>()
    private var trackerConfig: TrackerConfig? = null

    fun defaultAvatarConfig(block: AvatarConfigBuilder.() -> Unit) {
        val builder = AvatarConfigBuilder().apply(block)
        avatarConfig = builder.build()
    }

    fun registerBusiness(vararg business: AvatarBusinessData) {
        businesses.addAll(business)
    }

    fun TrackerConfig(block: TrackerConfigBuilder.() -> Unit) {
        val builder = TrackerConfigBuilder().apply(block)
        trackerConfig = builder.build()
    }

    fun build(): AvatarComponentConfig {
        Log.d(TAG, "build: ${avatarConfig}")
        return AvatarComponentConfig(
            avatarConfig ?: throw IllegalStateException("AvatarConfig must be set"),
            businesses.toList(),
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
    data class Business1Data(val type: BusinessDataSwitch) : BusinessData()
    data class Business2Data(val type: BusinessDataSwitch) : BusinessData()
}

enum class BusinessDataSwitch {
    ON,
    OFF
}

// 扩展函数：dp转px
fun Int.dpToPx(): Int = (this * getSystem().displayMetrics.density).toInt()
package com.example.myapplication.avatar.businessgradient


import android.graphics.Color
import com.example.avatar_api.model.AvatarDataState

class AvatarGradientRingState(
    val startColor: Int = Color.RED, // 默认蓝紫色
    val endColor: Int = Color.BLUE
) : AvatarDataState()
package com.example.myapplication.avatar.businessgradient

import androidx.compose.ui.graphics.Color
import com.example.avatar_api.model.AvatarDataState

class AvatarGradientRingState(
    val startColor: Color = Color(0xFF6A1B9A), // 默认蓝紫色
    val endColor: Color = Color(0xFF4A148C)
) : AvatarDataState()
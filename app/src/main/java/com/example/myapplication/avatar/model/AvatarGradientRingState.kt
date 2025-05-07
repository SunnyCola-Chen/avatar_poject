package com.example.myapplication.avatar.model

import androidx.compose.ui.graphics.Color

class AvatarGradientRingState(
    val startColor: Color = Color(0xFF6A1B9A), // 默认蓝紫色
    val endColor: Color = Color(0xFF4A148C)
) : AvatarDataState() 
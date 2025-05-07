package com.example.myapplication.avatar.model

import android.graphics.Color

open class AvatarUIData {
}

class AvatarRingUIData(var outline: ((avatarSize: Int) -> Int),
                       var width: ((avatarSize: Int) -> Int),
                       var color: Color)

class AvatarBadgeUIData(
    var position: AvatarNodePosition,
    var size: ((avatarSize: Int) -> Int),
    var color: Color
)
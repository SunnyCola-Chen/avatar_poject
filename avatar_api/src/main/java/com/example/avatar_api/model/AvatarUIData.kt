package com.example.avatar_api.model

import android.graphics.Color
import android.view.View

open class AvatarUIData(open val position: AvatarNodePosition,
    open val size: (avatarSize: Int) -> Int) {
}

class AvatarRingUIData(
    var outline: ((avatarSize: Int) -> Int),
    var strokeWidth: ((avatarSize: Int) -> Int),
    var color: Color
) : AvatarUIData(size = { avatarSize ->
    avatarSize + outline.invoke(avatarSize) * 2 + strokeWidth.invoke(
        avatarSize
    ) * 2
},
    position = AvatarNodePosition())

class AvatarBadgeUIData(
    val view: View? = null,
    override var position: AvatarNodePosition,
    override var size: ((avatarSize: Int) -> Int),
    var color: Color
): AvatarUIData(size = { avatarSize -> size.invoke(avatarSize)}, position = EdgePosition())
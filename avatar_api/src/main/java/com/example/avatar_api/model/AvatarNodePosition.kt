package com.example.avatar_api.model

open class AvatarNodePosition(
    val alignAvatar: Boolean = true,
    val marginEnd: ((avatarSize: Int) -> Int) = { 0 },
    val marginBottom: ((avatarSize: Int) -> Int) = { 0 }
)

class EdgePosition(
    alignAvatar: Boolean = true,
    marginEnd: ((avatarSize: Int) -> Int) = { 0 },
    marginBottom: ((avatarSize: Int) -> Int) = { 0 }
) : AvatarNodePosition(alignAvatar, marginEnd, marginBottom)

class CenterPosition(alignAvatar: Boolean = true,
                     marginEnd: ((avatarSize: Int) -> Int) = { 0 },
                     marginBottom: ((avatarSize: Int) -> Int) = { 0 }): AvatarNodePosition()
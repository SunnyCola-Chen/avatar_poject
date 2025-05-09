package com.example.avatar_api.core

import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.example.avatar_api.R
import com.example.avatar_api.model.AvatarBadgeUIData
import com.example.avatar_api.model.AvatarNodeType
import com.example.avatar_api.model.AvatarRingUIData
import com.example.avatar_api.model.AvatarUIData
import com.example.avatar_api.ui.AvatarRingView
import com.example.avatar_api.ui.CircleImageView
class AvatarNodeExecutor(val avatarContainer: RelativeLayout, val avatar: CircleImageView) {

    private val nodePrority = listOf(AvatarNodeType.RING, AvatarNodeType.AVATAR, AvatarNodeType.BADGE, AvatarNodeType.FRAME)
    fun updateAvatarNode(avatarData: MutableMap<AvatarNodeType, AvatarUIData>) {
        for (node in avatarData) {
            val avatarNodeType = node.key
            val avatarUIData = node.value
            val view = getView(avatarNodeType, avatarUIData)
            val lp = getPosition(avatarUIData)
            val index = nodePrority.indexOf(avatarNodeType)
            avatarContainer.addView(view, index, lp)
        }
    }

    private fun getView(avatarNodeType: AvatarNodeType, avatarUIData: AvatarUIData): View? {
        return when (avatarNodeType) {
            AvatarNodeType.RING -> (avatarUIData as? AvatarRingUIData)?.let {
                AvatarRingView(avatarContainer.context,
                    it
                )
            }
            AvatarNodeType.BADGE -> (avatarUIData as? AvatarBadgeUIData)?.let {
                avatarBadgeUIData -> avatarBadgeUIData.view
            }
            else -> null
        }
    }

    fun getPosition(avatarUIData: AvatarUIData): ViewGroup.LayoutParams {
        val position = avatarUIData.position
        // 如果和头像对齐则
        val avatarSize = avatar.width
        val size = avatarUIData.size.invoke(avatarSize)
        val lp = RelativeLayout.LayoutParams(size.dpToPx(), size.dpToPx())
        if (position.alignAvatar == true) {
            lp.apply {
                addRule(RelativeLayout.CENTER_HORIZONTAL, R.id.avatar)
                addRule(RelativeLayout.CENTER_VERTICAL, R.id.avatar)
            }
            lp.marginEnd = position.marginEnd.invoke(avatarSize)
            lp.bottomMargin = position.marginBottom.invoke(avatarSize)
        } else {
            lp.apply {
                addRule(RelativeLayout.ALIGN_BOTTOM, R.id.avatar)
                addRule(RelativeLayout.ALIGN_END, R.id.avatar)
            }
            lp.marginEnd = position.marginEnd.invoke(avatarSize)
            lp.bottomMargin = position.marginBottom.invoke(avatarSize)

        }
        return lp
    }

} 
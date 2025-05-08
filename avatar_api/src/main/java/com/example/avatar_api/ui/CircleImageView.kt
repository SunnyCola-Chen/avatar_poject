package com.example.avatar_api.ui

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import com.facebook.drawee.generic.RoundingParams
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.drawee.generic.GenericDraweeHierarchy
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder

class CircleImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : SimpleDraweeView(context, attrs, defStyle) {

    init {
        if (hierarchy == null) {
            hierarchy = createCircleHierarchy()
        } else {
            hierarchy.roundingParams = RoundingParams.asCircle()
        }
    }

    private fun createCircleHierarchy(): GenericDraweeHierarchy {
        return GenericDraweeHierarchyBuilder(resources)
            .setRoundingParams(RoundingParams.asCircle().apply {
                setBorder(Color.WHITE, 1f) // 可选边框
            })// 错误图
            .build()
    }
}
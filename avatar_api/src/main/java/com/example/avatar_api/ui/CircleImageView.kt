package com.example.avatar_api.ui

import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.util.AttributeSet
import com.facebook.drawee.generic.RoundingParams
import com.facebook.drawee.view.SimpleDraweeView

class CircleImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : SimpleDraweeView(context, attrs, defStyleAttr) {

    init {
        // 初始化圆形参数
        setupHierarchy()
    }

    private fun setupHierarchy() {
        val hierarchy = hierarchy ?: return
        // 设置为圆形（若需要圆角，可设置 cornerRadius）
        hierarchy.roundingParams = RoundingParams.asCircle()
            .setBorder(Color.WHITE, 2f) // 可选：添加白色边框
    }

    /** 加载网络图片 */
    fun setImageUrl(url: String) {
        val uri = Uri.parse(url)
        setImageURI(uri)
    }
}
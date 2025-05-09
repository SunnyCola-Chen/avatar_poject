package src.main.java.com.example.avatar_api

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.RelativeLayout
import com.example.avatar_api.core.AvatarComponentBuilder
import com.example.avatar_api.core.AvatarComponentConfig
import com.example.avatar_api.core.AvatarComponentDelegate

class AvatarComponentView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {
    companion object {
        val TAG = "AvatarComponentView-"
    }

    private var config: AvatarComponentConfig? = null
    private var avatarSize = 64
    private var avatarDelegate: AvatarComponentDelegate? = null

    fun buildAvatar(block: AvatarComponentBuilder.() -> Unit) {
        kotlin.runCatching {
            val builder = AvatarComponentBuilder().apply(block)
            config = builder.build() ?: return
            val cfg = config ?: return
            Log.d(TAG, "buildAvatar: ${cfg}")
            avatarDelegate = AvatarComponentDelegate(
                this@AvatarComponentView,
                cfg
            ).apply {
                buildAvatar()
            }
        }.onFailure { e ->
            Log.d(TAG, "buildAvatar error: ${e.message}")
        }
    }

    fun onBind(data: Any) {
        avatarDelegate?.onBind(data)
    }


}
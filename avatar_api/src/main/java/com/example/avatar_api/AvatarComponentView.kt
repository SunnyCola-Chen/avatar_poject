package src.main.java.com.example.avatar_api

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import com.example.avatar_api.AvatarComponentBuilder
import com.example.avatar_api.AvatarComponentConfig
import com.example.avatar_api.AvatarComponentDelegate

class AvatarComponentView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    private var config: AvatarComponentConfig? = null
    private var avatarSize = 64
    private var avatarDelegate: AvatarComponentDelegate? = null

    fun buildAvatar(block: AvatarComponentBuilder.() -> Unit) {
        val builder = AvatarComponentBuilder().apply(block)
        config = builder.build() ?: return
        val cfg = config ?: return
        avatarDelegate = AvatarComponentDelegate(
            this@AvatarComponentView,
            cfg
        ).apply {
            buildAvatar()
        }
    }

    fun onBind(data: Any) {
        avatarDelegate?.onBind(data)
    }


}
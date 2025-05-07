package com.example.myapplication.avatar

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LifecycleOwner
import com.example.myapplication.avatar.model.AvatarBusinessType
import com.example.myapplication.avatar.model.AvatarNodeType
import com.example.myapplication.avatar.model.AvatarVariant
import com.example.myapplication.avatar.model.AvatarDataState

class AvatarComponent(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): RelativeLayout(context, attrs, defStyleAttr) {

    private val avatarNodeExecutor = AvatarNodeExecutor()
    private val avatarController = AvatarController()


    init {
        avatarNodeExecutor.init(this@AvatarComponent)
//        avatarController.avatarLiveData.observe() {
//            nodeExecutor.execute(it)
//        }
    }

    fun buildAvatar(builder: (() -> Unit)) {

    }
}
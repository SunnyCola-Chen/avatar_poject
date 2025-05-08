package com.example.myapplication

import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import com.example.avatar_api.dpToPx
import com.example.avatar_api.model.AvatarBusinessType
import com.example.avatar_api.service.AvatarBusinessService
import com.example.myapplication.avatar.businessgradient.GradientRingBusinessConfig
import com.facebook.drawee.backends.pipeline.Fresco
import src.main.java.com.example.avatar_api.AvatarComponentView

class MainActivity : AppCompatActivity() {
    var avatarView: AvatarComponentView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Fresco.initialize(this)
        // 初始化头像组件的注入业务
        initAvatarComponent()

        // 创建布局
        val rootLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(16, 16, 16, 16)
        }

        // 创建头像视图
        avatarView = AvatarComponentView(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                300
            )
        }
        rootLayout.addView(avatarView)

        // 创建控制按钮
        val buttonLayout = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                topMargin = 16
            }
        }

        // 添加光环控制按钮
        val ringButton = Button(this).apply {
            text = "切换光环"
            setOnClickListener {
            }
        }
        buttonLayout.addView(ringButton)

        // 添加徽章控制按钮
        val badgeButton = Button(this).apply {
            text = "切换徽章"
            setOnClickListener {
            }
        }
        buttonLayout.addView(badgeButton)

        rootLayout.addView(buttonLayout)
        setContentView(rootLayout)

        // init avatar view
        val avatarComponentView: AvatarComponentView = AvatarComponentView(context = this)
        val lp = LinearLayout.LayoutParams(200, 200)
        avatarComponentView.layoutParams = lp
        rootLayout.addView(avatarComponentView)

        avatarComponentView.buildAvatar {
            defaultAvatarConfig {
                var containerAvatarSize: Int = 96.dpToPx()
                var avatarSize: Int = 90.dpToPx()
                var defaultClickListener: (() -> Unit)? = null
                var lifecycleOwner: LifecycleOwner? = null
            }
        }
        // input data
        avatarComponentView.onBind("")
    }


    private fun initAvatarComponent() {
        AvatarBusinessService.registerBusiness(AvatarBusinessType.BUSINESS_RING, GradientRingBusinessConfig())
    }

}
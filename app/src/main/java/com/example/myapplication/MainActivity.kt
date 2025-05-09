package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import com.example.avatar_api.User
import com.example.avatar_api.core.BusinessData
import com.example.avatar_api.core.BusinessDataSwitch
import com.example.avatar_api.core.dpToPx
import com.example.avatar_api.model.AvatarBusinessData
import com.example.avatar_api.model.AvatarBusinessType
import com.example.avatar_api.model.AvatarVariant
import com.example.avatar_api.service.AvatarBusinessService
import com.example.myapplication.avatar.businessgradient.GradientRingBusinessConfig
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.core.ImagePipelineConfig
import com.facebook.imagepipeline.listener.RequestLoggingListener
import src.main.java.com.example.avatar_api.AvatarComponentView

class MainActivity : AppCompatActivity() {
    companion object {
        val TAG = "MainActivity-"
    }
    var avatarView: AvatarComponentView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val imagePipelineConfig = ImagePipelineConfig.newBuilder(this)
            .setDownsampleEnabled(true)
            .setRequestListeners( // 监听网络请求
                setOf(
                    RequestLoggingListener() // 关键：添加日志监听器
                )
            )
            .build()
        Fresco.initialize(this, imagePipelineConfig)
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

        kotlin.runCatching {
            avatarComponentView.buildAvatar {
                defaultAvatarConfig {
                    var containerAvatarSize: Int = 96.dpToPx()
                    var avatarSize: Int = 90.dpToPx()
                    var defaultClickListener: (() -> Unit)? = null
                    var lifecycleOwner: LifecycleOwner? = null
                }

                registerBusiness(
                    AvatarBusinessData(
                        businessType = AvatarBusinessType.BUSINESS_RING,
                        variant = AvatarVariant()
                    ),
                    AvatarBusinessData(
                        businessType = AvatarBusinessType.BUSINESS_BADGE,
                        variant = AvatarVariant()
                    )
                )
            }
        }.onFailure { e ->
            Log.e(TAG, "onCreate: ${e.message}")
        }

        // input data
        avatarComponentView.onBind(
            User(
                uid = "1",
                business1 = BusinessData.Business1Data(BusinessDataSwitch.ON),
                business2 = BusinessData.Business2Data(BusinessDataSwitch.ON)
            )
        )
    }


    private fun initAvatarComponent() {
        AvatarBusinessService.registerBusiness(AvatarBusinessType.BUSINESS_RING, GradientRingBusinessConfig())
    }

}
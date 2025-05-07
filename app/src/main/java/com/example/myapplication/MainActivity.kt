package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.avatar.AvatarView
import com.example.myapplication.avatar.AvatarViewModel
import com.example.myapplication.avatar.model.AvatarNodeType
import com.example.myapplication.avatar.model.AvatarUIData

class MainActivity : AppCompatActivity() {
    private val viewModel: AvatarViewModel by viewModels()
    private lateinit var avatarView: AvatarView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // 创建布局
        val rootLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(16, 16, 16, 16)
        }

        // 创建头像视图
        avatarView = AvatarView(this).apply {
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
                toggleRing()
            }
        }
        buttonLayout.addView(ringButton)

        // 添加徽章控制按钮
        val badgeButton = Button(this).apply {
            text = "切换徽章"
            setOnClickListener {
                toggleBadge()
            }
        }
        buttonLayout.addView(badgeButton)

        rootLayout.addView(buttonLayout)
        setContentView(rootLayout)

        // 设置 ViewModel
        avatarView.setViewModel(viewModel, this)
    }

    private fun toggleRing() {
        val currentMap = viewModel.avatarLiveData.value ?: return
        if (currentMap.containsKey(AvatarNodeType.RING)) {
            viewModel.removeNodeData(AvatarNodeType.RING)
        } else {
            // 创建光环数据
            val ringData = AvatarUIData() // 根据实际需求设置数据
            viewModel.updateNodeData(AvatarNodeType.RING, ringData)
        }
    }

    private fun toggleBadge() {
        val currentMap = viewModel.avatarLiveData.value ?: return
        if (currentMap.containsKey(AvatarNodeType.BADGE)) {
            viewModel.removeNodeData(AvatarNodeType.BADGE)
        } else {
            // 创建徽章数据
            val badgeData = AvatarUIData() // 根据实际需求设置数据
            viewModel.updateNodeData(AvatarNodeType.BADGE, badgeData)
        }
    }
}
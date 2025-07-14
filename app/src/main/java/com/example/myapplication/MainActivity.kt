package com.example.myapplication

import android.animation.ValueAnimator
import android.app.ActionBar.LayoutParams
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.LinearLayout.HORIZONTAL
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.avatar_api.User
import com.example.avatar_api.core.BusinessData
import com.example.avatar_api.core.BusinessDataSwitch
import com.example.avatar_api.core.dpToPx
import com.example.avatar_api.model.AvatarBusinessData
import com.example.avatar_api.model.AvatarBusinessType
import com.example.avatar_api.model.AvatarVariant
import com.example.avatar_api.service.AvatarBusinessService
import com.example.myapplication.asyc.AsycLearnFragment
import com.example.myapplication.avatar.businessbadge.AvatarRedDotBadgeVariant
import com.example.myapplication.avatar.businessbadge.RedDotBadgeConfig
import com.example.myapplication.avatar.businessgradient.GradientRingBusinessConfig
import com.example.myapplication.dashhoardview.DashboardView
import com.example.myapplication.login.LoginActivity
import com.example.myapplication.login.model.LoginResponse
import com.example.myapplication.mytest.MyButton
import com.example.myapplication.mytest.MyLayout
import com.example.myapplication.scrollrecycleview.CardAdapter
import com.example.myapplication.scrollrecycleview.CardItem
import com.example.myapplication.scrollrecycleview.CenterSmoothScroller
import com.example.myapplication.scrollrecycleview.CenterZoomLayoutManager
import com.example.myapplication.scrolltest.InterceptTouchFrameLayout
import com.example.myapplication.scrolltest.MyPagerAdapter
import com.example.myapplication.viewdraw.SquareView
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.core.ImagePipelineConfig
import com.facebook.imagepipeline.listener.RequestLoggingListener
import com.google.android.material.snackbar.Snackbar
import src.main.java.com.example.avatar_api.AvatarComponentView
import kotlin.math.abs

class MainActivity : AppCompatActivity() {
    companion object {
        val TAG = "MainActivity-"
    }
    var avatarView: AvatarComponentView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        goLogin()
//        setContentView(R.layout.activity_top)
//        val fm = supportFragmentManager
//        val fragment = fm.findFragmentById(R.id.fragment_container)
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.fragment_container, AsycLearnFragment.newInstance())
//            .commit()

//        showDashboardView()
//        viewerList()
//        testViewDraw()
//        testScroll()
//        textEventDispatcher()
//        avatarRelativeBus()
    }

    fun goLogin() {
        val myButton = Button(this).apply {
            text = "Login"
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                300
            )
            setOnClickListener {
                Log.d(TAG, "Login button clicked!")
                val intent = Intent(this@MainActivity, LoginActivity::class.java)
                startActivity(intent)
            }
        }

        setContentView(myButton)
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var positionIndicator: TextView
    private lateinit var adapter: CardAdapter

    fun showDashboardView() {
        setContentView(R.layout.activity_dashboardview)
        val dashboardView = findViewById<DashboardView>(R.id.dashboard)
        val inputValue = findViewById<EditText>(R.id.inputValue)
        val submitButton = findViewById<Button>(R.id.submitButton)

        submitButton.setOnClickListener {
            val input = inputValue.text.toString()
            val targetValue = input.toIntOrNull()

            if (targetValue == null || targetValue !in 0..dashboardView.maxValue) {
                Toast.makeText(this, "请输入 0 ~ ${dashboardView.maxValue} 范围的数字", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            animateToValue(dashboardView, targetValue)
        }
    }
    fun animateToValue(dashboard: DashboardView, target: Int) {
        val animator = ValueAnimator.ofInt(dashboard.currentValue, target).apply {
            duration = 1000
            interpolator = AccelerateDecelerateInterpolator()
            addUpdateListener {
                dashboard.currentValue = it.animatedValue as Int
            }
        }
        animator.start()
    }


    fun viewerList() {
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.card_recycler)
        positionIndicator = findViewById(R.id.position_indicator)

        // 创建示例数据
        val items = listOf(
            CardItem("Mountain View", "https://images.unsplash.com/photo-1506905925346-21bda4d32df4?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1740&q=80"),
            CardItem("Ocean Sunset", "https://images.unsplash.com/photo-1507525428034-b723cf961d3e?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1746&q=80"),
            CardItem("Forest Path", "https://images.unsplash.com/photo-1448375240586-882707db888b?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1740&q=80"),
            CardItem("Desert Dunes", "https://images.unsplash.com/photo-1519681393784-d120267933ba?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1740&q=80"),
            CardItem("Northern Lights", "https://images.unsplash.com/photo-1483347756197-71ef80e95f73?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1740&q=80"),
            CardItem("City Skyline", "https://images.unsplash.com/photo-1477959858617-67f85cf4f1df?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1744&q=80"),
            CardItem("Mountain View", "https://images.unsplash.com/photo-1506905925346-21bda4d32df4?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1740&q=80"),
            CardItem("Ocean Sunset", "https://images.unsplash.com/photo-1507525428034-b723cf961d3e?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1746&q=80"),
            CardItem("Forest Path", "https://images.unsplash.com/photo-1448375240586-882707db888b?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1740&q=80"),
            CardItem("Desert Dunes", "https://images.unsplash.com/photo-1519681393784-d120267933ba?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1740&q=80"),
            CardItem("Northern Lights", "https://images.unsplash.com/photo-1483347756197-71ef80e95f73?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1740&q=80")
            )

        // 设置布局管理器
        val layoutManager = CenterZoomLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        val scroller = CenterSmoothScroller(recyclerView.context, recyclerView)

        // 设置适配器
        adapter = CardAdapter(items) { position, item ->
            Snackbar.make(recyclerView, "点击了: ${item.title}", Snackbar.LENGTH_SHORT).show()
        }
        recyclerView.adapter = adapter
        var isSnapping = false
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState != RecyclerView.SCROLL_STATE_IDLE) return
                val centerView =
                    (recyclerView.layoutManager as? CenterZoomLayoutManager)?.findCenterView() // 找到最居中的 item
                centerView?.let {
                    val position = recyclerView.getChildAdapterPosition(it)
                    isSnapping = true
                    scroller.targetPosition = position
                    Log.d(
                        TAG,
                        "onScrollStateChanged: ${newState} ${centerView.hashCode()} ${position}"
                    )
                    layoutManager.startSmoothScroll(scroller)
                    updatePositionIndicator(position)
                }
                recyclerView.postDelayed({isSnapping = true}, 300)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                Log.d(TAG, "onScrolled: ${dx}")
            }
        })

                // 初始位置指示器
        updatePositionIndicator(0)
    }

    private fun updatePositionIndicator(position: Int) {
        positionIndicator.text = "${position + 1}/${adapter.itemCount}"
    }

    fun testViewDraw() {
        val squareView = SquareView(this)
        setContentView(squareView, LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT))
    }

    fun testScroll() {
        val frameLayout = InterceptTouchFrameLayout(this)
        val viewPager = ViewPager2(this).apply {
            layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            adapter = MyPagerAdapter(this@MainActivity)
        }
        frameLayout.addView(viewPager)
        setContentView(frameLayout)
        frameLayout.setOnTouchListener { v, event ->
            Log.d(TAG, "testScroll: ${event}")
            (viewPager.getChildAt(0) as? RecyclerView)?.dispatchTouchEvent(event) == true
        }
    }


    private fun textEventDispatcher() {
        val myLayout = MyLayout(this).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }

        val myButton = MyButton(this).apply {
            text = "Click Me 1"
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                300
            )
            setOnClickListener {
                Log.d("MyButton1", "Button1 clicked!")
            }
        }

        val myButton2 = MyButton(this).apply {
            text = "Click Me 2"
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                300
            )
            setOnClickListener {
                Log.d("MyButton2", "Button2 clicked!")
            }
        }

        myLayout.addView(myButton)
        myLayout.addView(myButton2)
        setContentView(myLayout)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.d(TAG, "onTouchEvent: ${event}")
        return super.onTouchEvent(event)
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        Log.d(TAG, "\n \n dispatchTouchEvent: ${ev}")
        return super.dispatchTouchEvent(ev)
    }















    private fun avatarRelativeBus() {
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
        val lp = LinearLayout.LayoutParams(900, 900)
        avatarComponentView.layoutParams = lp
        rootLayout.addView(avatarComponentView)

        kotlin.runCatching {
            avatarComponentView.buildAvatar {
                defaultAvatarConfig {
                    containerAvatarSize = 100.dpToPx()
                    avatarSize = 90.dpToPx()
                    defaultClickListener = null
                    lifecycleOwner = this@MainActivity
                }

                registerBusiness(
                    AvatarBusinessData(
                        businessType = AvatarBusinessType.BUSINESS_RING,
                        variant = AvatarVariant()
                    ),
                    AvatarBusinessData(
                        businessType = AvatarBusinessType.BUSINESS_BADGE,
                        variant = AvatarRedDotBadgeVariant(ctx = this@MainActivity)
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
        AvatarBusinessService.registerBusiness(AvatarBusinessType.BUSINESS_BADGE, RedDotBadgeConfig())
    }

}
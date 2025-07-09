package com.example.myapplication.mytest

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.LinearLayout

class MyLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {
    init {
        orientation = VERTICAL
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        Log.d("MyLayout", "dispatchTouchEvent: ${ev}")
        return super.dispatchTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        Log.d("MyLayout", "onInterceptTouchEvent: ${ev}")
        // 拦截事件测试：改为 true 表示子 View 不会收到事件
        return false
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        Log.d("MyLayout", "onTouchEvent: ${event}")
        return false
    }
}
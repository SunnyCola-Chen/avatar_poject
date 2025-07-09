package com.example.myapplication.mytest

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatButton

class MyButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : AppCompatButton(context, attrs) {

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        Log.d("MyButton-${text}", "dispatchTouchEvent: ${ev}")
        return super.dispatchTouchEvent(ev)
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        Log.d("MyButton-${text}", "onTouchEvent: ${event}")
        return false
    }
}

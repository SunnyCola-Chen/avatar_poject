package com.example.myapplication.scrollrecycleview

import android.content.Context
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView

class CenterSmoothScroller(
    context: Context,
    private val recyclerView: RecyclerView
) : LinearSmoothScroller(context) {
    val TAG = "CenterSmoothScroller"
    val scrollSpeedFactor: Float = 2.0f

    override fun calculateDxToMakeVisible(view: View, snapPreference: Int): Int {
        val layoutManager = recyclerView.layoutManager ?: return 0
        val parentCenter = recyclerView.width / 2
        val childCenter = (layoutManager.getDecoratedLeft(view) + layoutManager.getDecoratedRight(view)) / 2
        Log.d(TAG, "calculateDxToMakeVisible: ${view.hashCode()} ${childCenter} ${parentCenter}")
        return parentCenter - childCenter
    }


    override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics?): Float {
        return (25f * scrollSpeedFactor) / ((displayMetrics?.densityDpi) ?: 1)
    }

    override fun getHorizontalSnapPreference(): Int = SNAP_TO_START

}

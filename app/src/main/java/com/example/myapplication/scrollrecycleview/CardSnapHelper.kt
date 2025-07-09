package com.example.myapplication.scrollrecycleview

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper

// 自定义SnapHelper实现吸附效果，没用到，因为这个自动吸附和惯性会有冲突，导致不能惯性滑动。
class CardSnapHelper(private val onSnap: (Int) -> Unit) : SnapHelper() {

    private var recyclerView: RecyclerView? = null

    override fun attachToRecyclerView(recyclerView: RecyclerView?) {
        super.attachToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    override fun findSnapView(layoutManager: RecyclerView.LayoutManager?): View? {
        return if (layoutManager is CenterZoomLayoutManager) {
            layoutManager.findCenterView()
        } else {
            null
        }
    }

    override fun findTargetSnapPosition(
        layoutManager: RecyclerView.LayoutManager?,
        velocityX: Int,
        velocityY: Int
    ): Int {
        if (layoutManager !is LinearLayoutManager) return RecyclerView.NO_POSITION

        val centerView = findSnapView(layoutManager) ?: return RecyclerView.NO_POSITION
        val position = layoutManager.getPosition(centerView)

        // 确定滚动方向
        val isForward = velocityX > 0

        return if (isForward) {
            position + 1
        } else {
            position - 1
        }.coerceIn(0, layoutManager.itemCount - 1)
    }

    override fun calculateDistanceToFinalSnap(
        layoutManager: RecyclerView.LayoutManager,
        targetView: View
    ): IntArray? {
        if (layoutManager !is CenterZoomLayoutManager) return null

        val center = layoutManager.width / 2
        val targetCenter = (layoutManager.getDecoratedLeft(targetView) +
                layoutManager.getDecoratedRight(targetView)) / 2

        return intArrayOf(targetCenter - center, 0)
    }

    override fun onFling(velocityX: Int, velocityY: Int): Boolean {
        val layoutManager = recyclerView?.layoutManager ?: return false
        val minFlingVelocity = recyclerView?.minFlingVelocity ?: 0

        return (kotlin.math.abs(velocityX) > minFlingVelocity).also {
            if (it) {
                val position = findTargetSnapPosition(layoutManager, velocityX, velocityY)
                if (position != RecyclerView.NO_POSITION) {
                    // 延迟一小段时间再 snap，否则会立刻吸附
                    recyclerView?.postDelayed({
                        recyclerView?.smoothScrollToPosition(position)
                    }, 100)

                    onSnap(position)
                }
            }
        }
    }
}
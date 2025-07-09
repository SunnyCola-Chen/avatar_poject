package com.example.myapplication.scrollrecycleview

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// 自定义LayoutManager实现放大效果
class CenterZoomLayoutManager(context: android.content.Context) :
    LinearLayoutManager(context, HORIZONTAL, false) {

        val TAG = "CenterZoomLayoutManager"

    private val shrinkAmount = 0.3f // 缩放比例
    private val shrinkDistance = 0.9f // 缩放距离系数
    // 记录当前滑动的偏移量
    private var horizontalScrollOffset = 0

    override fun onLayoutChildren(recycler: RecyclerView.Recycler, state: RecyclerView.State) {
        detachAndScrapAttachedViews(recycler)

        if (itemCount == 0) {
            horizontalScrollOffset = 0
            return
        }

        var leftOffset: Int

        val parentWidth = width
        val firstView = recycler.getViewForPosition(0)
        addView(firstView)
        measureChildWithMargins(firstView, 0, 0)

        val decoratedWidth = getDecoratedMeasuredWidth(firstView)
        val decoratedHeight = getDecoratedMeasuredHeight(firstView)

        // 计算第一个item左边起点，使其居中
        val firstLeft = (parentWidth - decoratedWidth) / 2 - horizontalScrollOffset

        layoutDecoratedWithMargins(
            firstView,
            firstLeft,
            paddingTop,
            firstLeft + decoratedWidth,
            paddingTop + decoratedHeight
        )

        leftOffset = firstLeft + decoratedWidth

        // 布局剩余的item
        for (pos in 1 until itemCount) {
            val view = recycler.getViewForPosition(pos)
            addView(view)
            measureChildWithMargins(view, 0, 0)
            val w = getDecoratedMeasuredWidth(view)
            val h = getDecoratedMeasuredHeight(view)

            layoutDecoratedWithMargins(
                view,
                leftOffset,
                paddingTop,
                leftOffset + w,
                paddingTop + h
            )
            leftOffset += w
        }

        // 布局完成后，执行缩放动画
        scaleChildren()
    }


    override fun scrollHorizontallyBy(
        dx: Int,
        recycler: RecyclerView.Recycler?,
        state: RecyclerView.State?
    ): Int {
        val delta = scrollHorizontallyInternal(dx)
        offsetChildrenHorizontal(-delta)
        scaleChildren()
        return delta
    }

    private fun scrollHorizontallyInternal(dx: Int): Int {
        if (childCount == 0) return 0

        val maxScroll = getMaxScroll()
        val pendingScroll = horizontalScrollOffset + dx

        horizontalScrollOffset = when {
            pendingScroll < 0 -> 0
            pendingScroll > maxScroll -> maxScroll
            else -> pendingScroll
        }

        return horizontalScrollOffset - (horizontalScrollOffset - dx)
    }

    private fun getMaxScroll(): Int {
        if (childCount == 0) return 0
        val lastView = getChildAt(childCount - 1) ?: return 0
        val lastPos = getPosition(lastView)
        val lastDecoratedRight = getDecoratedRight(lastView)
        val parentWidth = width

        // 最大偏移量是使最后一个item的右边靠齐RecyclerView右边缘
        return lastDecoratedRight - parentWidth + (parentWidth - getDecoratedMeasuredWidth(lastView)) / 2
    }


    private fun scaleChildren() {
        val midpoint = width / 2f
        val d1 = shrinkDistance * midpoint

        for (i in 0 until childCount) {
            val child = getChildAt(i) ?: continue
            val childMidpoint = (getDecoratedRight(child) + getDecoratedLeft(child)) / 2f
            val d = d1.coerceAtMost(kotlin.math.abs(midpoint - childMidpoint))
            val scale = 1f - shrinkAmount * (d / d1)
            child.scaleX = scale
            child.scaleY = scale

            // 设置透明度变化
            child.alpha = 0.5f + (scale * 0.5f)
        }
    }


    fun findCenterView(): View? {
        val childCount = childCount
        if (childCount == 0) return null

        var closestChild: View? = null
        var absClosest = Int.MAX_VALUE
        val center = width / 2

        for (i in 0 until childCount) {
            val child = getChildAt(i) ?: continue
            val childCenter = (getDecoratedLeft(child) + getDecoratedRight(child)) / 2
            val absDistance = kotlin.math.abs(childCenter - center)

            if (absDistance < absClosest) {
                Log.d(TAG, "findCenterView: ${i} ${child.hashCode()} ${childCenter} ${center}。${Throwable().stackTrace[1]}")
                absClosest = absDistance
                closestChild = child
            }
        }
        return closestChild
    }
}

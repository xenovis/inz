package com.xenovis.planszowki.common

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.util.TypedValue
import android.view.View
import com.xenovis.planszowki.R

class ListingItemDecoration(context: Context) : RecyclerView.ItemDecoration() {

    private val mPaddingLeft: Float
    private val mDividerPaint: Paint
    private val mWhitePaint: Paint
    private val shadow: Drawable

    init {
        mDividerPaint = Paint()
        mWhitePaint = Paint()
        val thickness = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1f, context.resources.displayMetrics)
        mDividerPaint.color = ContextCompat.getColor(context, R.color.semitransparentBlack)
        mWhitePaint.color = ContextCompat.getColor(context, android.R.color.white)
        mPaddingLeft = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 70f, context.resources.displayMetrics)
        mWhitePaint.strokeWidth = thickness
        mDividerPaint.strokeWidth = thickness
        shadow = ContextCompat.getDrawable(context, R.drawable.shadow)
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
        outRect.set(0, 0, 0, mDividerPaint.strokeWidth.toInt())
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State?) {
        val offset = (mDividerPaint.strokeWidth / 2).toInt()

        if (parent.childCount == 1) {
            val child = parent.getChildAt(0)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val left = parent.paddingLeft
            val right = parent.width - parent.paddingRight
            val top = child.bottom + params.bottomMargin
            val bottom = top + shadow.intrinsicHeight
            shadow.setBounds(left, top, right, bottom)
            shadow.draw(c)
        } else {
            for (i in 0..parent.childCount - 1) {
                val child = parent.getChildAt(i)
                val params = child.layoutParams as RecyclerView.LayoutParams
                val position = params.viewAdapterPosition
                if (position < state!!.itemCount - 1) {
                    c.drawLine(child.left.toFloat(), (child.bottom + offset).toFloat(), mPaddingLeft, (child.bottom + offset).toFloat(), mWhitePaint)
                    c.drawLine(mPaddingLeft, (child.bottom + offset).toFloat(), child.right.toFloat(), (child.bottom + offset).toFloat(), mDividerPaint)
                } else if (position == state.itemCount - 1 && state.itemCount != 1) {
                    val left = parent.paddingLeft
                    val right = parent.width - parent.paddingRight
                    val top = child.bottom + params.bottomMargin
                    val bottom = top + shadow.intrinsicHeight
                    shadow.setBounds(left, top, right, bottom)
                    shadow.draw(c)
                }
            }
        }
    }
}

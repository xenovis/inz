package com.xenovis.planszowki.screen.main.fragment.ranking.games

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

class RankingItemDecoration(context: Context) : RecyclerView.ItemDecoration() {

    private val paddingLeft: Float
    private val dividerPaint: Paint
    private val whitePaint: Paint
    private val shadow: Drawable

    init {
        dividerPaint = Paint()
        whitePaint = Paint()
        val thickness = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1f, context.resources.displayMetrics)
        dividerPaint.color = ContextCompat.getColor(context, R.color.semitransparentBlack)
        whitePaint.color = ContextCompat.getColor(context, android.R.color.white)
        paddingLeft = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 116f, context.resources.displayMetrics)
        whitePaint.strokeWidth = thickness
        dividerPaint.strokeWidth = thickness
        shadow = ContextCompat.getDrawable(context, R.drawable.shadow)
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
        outRect.set(0, 0, 0, dividerPaint.strokeWidth.toInt())
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State?) {
        val offset = (dividerPaint.strokeWidth / 2).toInt()

        for (i in 0..parent.childCount - 1) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val position = params.viewAdapterPosition
            if (position < state!!.itemCount - 1) {
                c.drawLine(child.left.toFloat(), (child.bottom + offset).toFloat(), paddingLeft, (child.bottom + offset).toFloat(), whitePaint)
                c.drawLine(paddingLeft, (child.bottom + offset).toFloat(), child.right.toFloat(), (child.bottom + offset).toFloat(), dividerPaint)
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

package com.xenovis.planszowki.widget

import android.content.Context
import android.os.Looper
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.xenovis.planszowki.R
import kotlinx.android.synthetic.main.single_rating_layout.view.*
import java.util.*


/**
 * Created by maciek on 04/12/16.
 */

class RatingLayout(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {

    var ratingNameTextView: TextView? = null
    var stars = ArrayList<ImageView>()
    private var listener: RatingListener? = null

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.single_rating_layout, this, false) as LinearLayout
        addView(view)
        stars.add(star_1)
        stars.add(star_2)
        stars.add(star_3)
        stars.add(star_4)
        stars.add(star_5)
        setupListeners()
        var text = ""
        val a = context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.RatingLayout,
                0, 0)

        try {
            text = a.getString(R.styleable.RatingLayout_text)
        } finally {
            a.recycle()
        }
        setup(text)
    }

    private fun setupListeners() {
        for (i in 0..4) {
            stars[i].setOnClickListener {
                checkStars(i+1)
                listener?.onRatingSelected(i+1)
            }
        }
    }

    private fun setup(text: String) {
        setText(text)
    }

    private fun checkStars(rating: Int) {
        for (i in 0 .. 4) {
            stars[i].setChecked(if( rating > i ) true else false)
        }
    }

    private fun setText(text: String) {
        ratingNameTextView = findViewById(R.id.rating_name_textview) as TextView
        ratingNameTextView?.text = text
    }

    fun setUserRating(rating: Int) {
        println("rating = [${rating}]")
        android.os.Handler(Looper.getMainLooper()).post { checkStars(rating) }
    }

    interface RatingListener {
        fun onRatingSelected(rating: Int)
    }

    fun setRatingListener(listener: RatingListener) {
        this.listener = listener
    }

    fun ImageView.setChecked(checked: Boolean) {
        this.setImageDrawable(if (checked) ContextCompat.getDrawable(context, R.drawable.ic_star_32dp) else ContextCompat.getDrawable(context, R.drawable.ic_star_outline_32dp))
    }
}
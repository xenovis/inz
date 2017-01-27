package com.xenovis.planszowki.widget

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import com.xenovis.planszowki.R
import com.xenovis.planszowki.utils.KeyboardUtils

/**
 * Created by maciek on 03/12/16.
 */
class SearchLayout (context: Context, attrs: AttributeSet): FrameLayout(context, attrs) {

    var searchEditText: EditText? = null
    private var listener: SearchListener? = null

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.search_layout, this, false) as LinearLayout
        addView(view)
        setupTextWatcher()
    }

    private fun setupTextWatcher() {
        searchEditText = findViewById(R.id.edittext_search) as EditText
        val searchDeleteImageView = findViewById(R.id.imageview_search_delete) as ImageView
        searchDeleteImageView.setOnClickListener { v1 -> searchEditText?.setText("") }
        searchEditText?.setOnEditorActionListener({ v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) listener?.onSearchClicked(searchEditText?.text.toString())
            KeyboardUtils.hideKeyBoard(searchEditText?.applicationWindowToken)
            true
        })
        searchEditText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                if (s.toString() == "") searchDeleteImageView.visibility = View.GONE
                else searchDeleteImageView.visibility = View.VISIBLE
            }
        })
    }

    fun clearText() {
        val searchEditText = findViewById(R.id.edittext_search) as EditText
        searchEditText.setText("")
    }

    interface SearchListener {
        fun onSearchClicked(string: String)
    }

    fun setOnSearchClickedListener(listener: SearchListener) {
        this.listener = listener
    }
}
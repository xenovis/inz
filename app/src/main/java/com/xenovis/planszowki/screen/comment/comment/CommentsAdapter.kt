package com.xenovis.planszowki.screen.comment.comment

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xenovis.planszowki.R
import com.xenovis.planszowki.data.api.response.Comment
import kotlinx.android.synthetic.main.comment_itemview.view.*

/**
 * Created by maciek on 04/12/16.
 */
class CommentsAdapter(val list: List<Comment>) : RecyclerView.Adapter<CommentsViewHolder>(){
    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        holder.bindView(list[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.comment_itemview, parent, false)
        return CommentsViewHolder(view)
    }
}

class CommentsViewHolder(view: View?) : RecyclerView.ViewHolder(view){

    fun bindView(comment: Comment) {
        itemView.username_textview.text = comment.username
        itemView.message_textview.text = comment.message
        itemView.date_textview.text = comment.date.toString()
    }

}

package com.xenovis.planszowki.screen.main.fragment.categories.categories

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xenovis.planszowki.R
import com.xenovis.planszowki.data.api.response.Category
import kotlinx.android.synthetic.main.categories_list_itemview.view.*

/**
 * Created by maciek on 03/12/16.
 */
class CategoriesAdapter(var list: List<Category>, var listener: CategoriesListener) : RecyclerView.Adapter<CategoriesViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.categories_list_itemview, parent, false)
        return CategoriesViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.bindView(list[position])
    }

    override fun getItemCount(): Int {
        return list.count()
    }
}

class CategoriesViewHolder(view: View?, val listener: CategoriesListener) : RecyclerView.ViewHolder(view) {
    fun bindView(category: Category) {
        itemView.setOnClickListener { listener.onClicked(category.category) }
        itemView.category_textview.text = category.category
        itemView.count_textview.text = itemView.context.resources.getQuantityString(R.plurals.games, category.count, category.count)
    }

}

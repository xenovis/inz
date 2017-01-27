package com.xenovis.planszowki.common

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.xenovis.planszowki.BuildConfig
import com.xenovis.planszowki.R
import com.xenovis.planszowki.data.api.response.ListedBoardgame
import kotlinx.android.synthetic.main.boardgame_rankinglist_itemview.view.*

/**
 * Created by maciek on 03/12/16.
 */
class BoardgamesAdapter(val list: List<ListedBoardgame>, val listener: BoardgamesListener) : RecyclerView.Adapter<BoardgamesViewHolder>() {
    override fun onBindViewHolder(holder: BoardgamesViewHolder, position: Int) {
        holder.bindView(list[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardgamesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.boardgame_normallist_itemview, parent, false)
        return BoardgamesViewHolder(view, listener)
    }

    override fun getItemCount(): Int {
        return list.count()
    }
}

class BoardgamesViewHolder(view: View?, val listener: BoardgamesListener) : RecyclerView.ViewHolder(view) {
    fun bindView(listedBoardgame: ListedBoardgame) {
        Picasso.with(itemView.context).load(BuildConfig.BASE_API_URL + '/' + listedBoardgame.logoUrl).into(itemView.game_logo_imageview)
        itemView.game_name_textview.text = listedBoardgame.name
        itemView.game_year_textview.text = listedBoardgame.productionYear.toString()
        itemView.game_score_textview.text = listedBoardgame.averageAwesomenessRating.toString()
        itemView.setOnClickListener { listener.onBoardgameClicked(listedBoardgame.name) }
    }

}

package com.xenovis.planszowki.screen.main.fragment.ranking.games

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.xenovis.planszowki.BuildConfig
import com.xenovis.planszowki.R
import com.xenovis.planszowki.common.BoardgamesListener
import com.xenovis.planszowki.data.api.response.ListedBoardgame
import kotlinx.android.synthetic.main.boardgame_rankinglist_itemview.view.*

/**
 * Created by maciek on 30/11/16.
 */
class RankingAdapter(list: List<ListedBoardgame>, val listener: BoardgamesListener) : RecyclerView.Adapter<RankingViewHolder>() {

    var list = list

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: RankingViewHolder?, position: Int) {
        holder?.bindView(list[position], position+1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankingViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.boardgame_rankinglist_itemview, parent, false)
        return RankingViewHolder(view, listener)
    }

}

class RankingViewHolder(view: View?, val listener: BoardgamesListener) : RecyclerView.ViewHolder(view){

    fun bindView(listedBoardgame: ListedBoardgame, position: Int) {
        println(BuildConfig.BASE_API_URL + '/' + listedBoardgame.logoUrl)
        Picasso.with(itemView.context).load(BuildConfig.BASE_API_URL + '/' + listedBoardgame.logoUrl).into(itemView.game_logo_imageview)
        itemView.rank_textview.text = itemView.context.getString(R.string.rank, position)
        itemView.game_name_textview.text = listedBoardgame.name
        itemView.game_year_textview.text = listedBoardgame.productionYear.toString()
        itemView.game_score_textview.text = listedBoardgame.averageAwesomenessRating.toString()
        itemView.setOnClickListener { listener.onBoardgameClicked(listedBoardgame.name) }
    }

}


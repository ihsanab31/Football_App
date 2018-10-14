package com.ofal.ihsan.footballapp.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.ofal.ihsan.footballapp.adapter.viewholder.MatchesViewHolder
import com.ofal.ihsan.footballapp.model.db.MatchDB
import com.ofal.ihsan.footballapp.view.basicui.ItemMatchesUI
import org.jetbrains.anko.AnkoContext

/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 085749729115
 * on       : 19, September, 2018
 * ------------------------------
 * This class for adapter favorite match
 */
class FavoriteMatchesAdapter (private val favoriteMatches: List<MatchDB>,
                              private val listener: (MatchDB) -> Unit):
        RecyclerView.Adapter<MatchesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchesViewHolder {
        return MatchesViewHolder(ItemMatchesUI().createView(AnkoContext.
                create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: MatchesViewHolder, position: Int) {
        holder.bindFavorite(favoriteMatches[position], listener)
    }

    override fun getItemCount(): Int = favoriteMatches.size
}
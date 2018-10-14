package com.ofal.ihsan.footballapp.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.ofal.ihsan.footballapp.adapter.viewholder.MatchesViewHolder
import com.ofal.ihsan.footballapp.model.Event
import com.ofal.ihsan.footballapp.view.basicui.ItemMatchesUI
import org.jetbrains.anko.AnkoContext

/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 085749729115
 * on       : 19, September, 2018
 * ------------------------------
 * This class for match adapter
 */
class MatchesAdapater (private val matchs: List<Event>) :
        RecyclerView.Adapter<MatchesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchesViewHolder {
        return MatchesViewHolder(ItemMatchesUI().createView(AnkoContext.create(parent.context,
                parent)))
    }

    override fun getItemCount(): Int = matchs.size

    override fun onBindViewHolder(holder: MatchesViewHolder, position: Int) {
        holder.bindItem(matchs[position])
    }

}
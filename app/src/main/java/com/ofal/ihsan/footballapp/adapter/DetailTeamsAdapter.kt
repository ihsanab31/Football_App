package com.ofal.ihsan.footballapp.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.ofal.ihsan.footballapp.adapter.viewholder.ListPlayerViewHolder
import com.ofal.ihsan.footballapp.model.Player
import com.ofal.ihsan.footballapp.view.basicui.ItemListPlayerUI
import org.jetbrains.anko.AnkoContext

/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 085749729115
 * on       : 19, September, 2018
 * ------------------------------
 * This class for detail adapter
 */
class DetailTeamsAdapter (private val players: List<Player>) :
        RecyclerView.Adapter<ListPlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPlayerViewHolder {
        return ListPlayerViewHolder(ItemListPlayerUI().createView(AnkoContext.create(parent.context,
                parent)))
    }

    override fun getItemCount(): Int = players.size

    override fun onBindViewHolder(holder: ListPlayerViewHolder, position: Int) {
        holder.bindItem(players[position])
    }
}
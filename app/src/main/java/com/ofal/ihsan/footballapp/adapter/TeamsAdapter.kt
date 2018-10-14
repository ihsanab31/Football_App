package com.ofal.ihsan.footballapp.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.ofal.ihsan.footballapp.adapter.viewholder.TeamViewHolder
import com.ofal.ihsan.footballapp.model.Team
import com.ofal.ihsan.footballapp.view.basicui.ItemTeamsUI
import org.jetbrains.anko.AnkoContext

/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 085749729115
 * on       : 19, September, 2018
 * ------------------------------
 * This class for team adapter
 */
class TeamsAdapter (private val teams: List<Team>)
    : RecyclerView.Adapter<TeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder(ItemTeamsUI().createView(AnkoContext.create(parent.context,
                parent)))
    }

    override fun getItemCount(): Int = teams.size

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bindItem(teams[position])
    }
}
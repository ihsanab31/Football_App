package com.ofal.ihsan.footballapp.adapter.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.ofal.ihsan.footballapp.R
import com.ofal.ihsan.footballapp.R.string.item_teamdetail_id
import com.ofal.ihsan.footballapp.model.Team
import com.ofal.ihsan.footballapp.model.db.TeamDB
import com.ofal.ihsan.footballapp.view.activities.detail.team.DetailTeamsActivity
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity

/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 085749729115
 * on       : 19, September, 2018
 * ------------------------------
 * This class for view holder team
 */
class TeamViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    private val teamBadge: ImageView = view.find(R.id.team_badge)
    private val teamName: TextView = view.find(R.id.team_name)

    fun bindItem(teams: Team) {
        Picasso.get().load(teams.teamBadge).into(teamBadge)
        teamName.text = teams.teamName

        val ctx = itemView.context

        itemView.setOnClickListener {
            ctx.startActivity<DetailTeamsActivity>(
                    ctx.getString(item_teamdetail_id) to teams.teamId)
        }
    }

    fun bindFavorite(favorite: TeamDB, listener: (TeamDB) -> Unit) {
        Picasso.get().load(favorite.badgeTeam).into(teamBadge)
        teamName.text = favorite.teamName

        itemView.onClick {
            listener(favorite)
        }
    }
}
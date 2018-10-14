package com.ofal.ihsan.footballapp.adapter.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.ofal.ihsan.footballapp.R
import com.ofal.ihsan.footballapp.model.Player
import com.ofal.ihsan.footballapp.view.activities.detail.player.DetailPlayersActivity
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 085749729115
 * on       : 19, September, 2018
 * ------------------------------
 * This class for view holder player
 */
class ListPlayerViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    private val ivPlayer: ImageView = view.find(R.id.ivListPlayer)
    private val namePlayer: TextView = view.find(R.id.tvListNamePlayer)
    private val namePosition: TextView = view.find(R.id.tvPositionListPlayer)

    fun bindItem(players: Player) {
        Picasso.get().load(players.strCutout).into(ivPlayer)
        namePlayer.text = players.strPlayer
        namePosition.text = players.strPosition

        val ctx = itemView.context

        itemView.setOnClickListener {
            ctx.startActivity<DetailPlayersActivity>(
                    ctx.getString(R.string.item_teamplayer_id) to players.playerId)
        }
    }
}
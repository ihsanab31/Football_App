package com.ofal.ihsan.footballapp.model

import com.google.gson.annotations.SerializedName

/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 085749729115
 * on       : 19, September, 2018
 * ------------------------------
 * This class for player response
 */
data class PlayerResponse (
        val player: List<Player>,
        @SerializedName("players")
        val players: List<Player>
)
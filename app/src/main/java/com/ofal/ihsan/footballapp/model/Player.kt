package com.ofal.ihsan.footballapp.model

import com.google.gson.annotations.SerializedName

/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 085749729115
 * on       : 19, September, 2018
 * ------------------------------
 * This class for player model
 */
data class Player(
        @SerializedName("idPlayer") var playerId: String? = null,
        @SerializedName("strPlayer") var strPlayer: String? = null,
        @SerializedName("strPosition") var strPosition: String? = null,
        @SerializedName("strCutout") var strCutout: String? = null,
        @SerializedName("strDescriptionEN") var strDescription: String? = null,
        @SerializedName("strThumb") var strThumb: String? = null,
        @SerializedName("strHeight") var strHeight: String? = null,
        @SerializedName("strWeight") var strWeight: String? = null,
        @SerializedName("strFanart1") var strFanart1: String? = null
)
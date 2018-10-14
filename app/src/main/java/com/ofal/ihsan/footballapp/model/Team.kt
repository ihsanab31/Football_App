package com.ofal.ihsan.footballapp.model

import com.google.gson.annotations.SerializedName

/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 085749729115
 * on       : 19, September, 2018
 * ------------------------------
 * This class for team model
 */
data class Team(
        @SerializedName("idTeam") var teamId: String? = null,
        @SerializedName("strTeam") var teamName: String? = null,
        @SerializedName("strTeamBadge") var teamBadge: String? = null,
        @SerializedName("strStadium") var stadiumName: String? = null,
        @SerializedName("intFormedYear") var formedYear: String? = null,
        @SerializedName("strDescriptionEN") var descriptionTeam: String? = null
)
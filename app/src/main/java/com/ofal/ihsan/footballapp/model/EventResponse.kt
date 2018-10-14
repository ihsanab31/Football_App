package com.ofal.ihsan.footballapp.model

import com.google.gson.annotations.SerializedName

/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 085749729115
 * on       : 19, September, 2018
 * ------------------------------
 * This class for response event
 */
data class EventResponse(
        @SerializedName("events")
        val event: List<Event>,
        @SerializedName("event")
        val searchEvent: List<Event>
)
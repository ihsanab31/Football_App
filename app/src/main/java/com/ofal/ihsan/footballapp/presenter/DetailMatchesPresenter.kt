package com.ofal.ihsan.footballapp.presenter

import com.google.gson.Gson
import com.ofal.ihsan.footballapp.api.ApiRequest
import com.ofal.ihsan.footballapp.api.TheSportApi
import com.ofal.ihsan.footballapp.model.EventResponse
import com.ofal.ihsan.footballapp.model.TeamResponse
import com.ofal.ihsan.footballapp.utils.CoroutineContextProvider
import com.ofal.ihsan.footballapp.view.activities.detail.match.DetailMatchesView
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class DetailMatchesPresenter(private val view: DetailMatchesView,
                             private val apiRequest: ApiRequest,
                             private val gson: Gson,
                             private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getEventDetail(idEvent: String?, idHomeTeam: String?, idAwayTeam: String?) {
        view.showLoading()

        async(context.main) {
            val eventDetail = bg {
                gson.fromJson(apiRequest.doRequest(TheSportApi.getDetailEvent(idEvent)),
                        EventResponse::class.java)
            }
            val badgeHome = bg {
                gson.fromJson(apiRequest.doRequest(TheSportApi.getHomeBadge(idHomeTeam)),
                        TeamResponse::class.java)
            }
            val badgeAway =  bg {
                gson.fromJson(apiRequest.doRequest(TheSportApi.getAwayBadge(idAwayTeam)),
                        TeamResponse::class.java)
            }
            view.showEventList(eventDetail.await().event, badgeHome.await().team,
                    badgeAway.await().team)
            view.hideLoading()
        }
    }
}
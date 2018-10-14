package com.ofal.ihsan.footballapp.presenter

import com.google.gson.Gson
import com.ofal.ihsan.footballapp.api.ApiRequest
import com.ofal.ihsan.footballapp.api.TheSportApi
import com.ofal.ihsan.footballapp.model.PlayerResponse
import com.ofal.ihsan.footballapp.model.TeamResponse
import com.ofal.ihsan.footballapp.utils.CoroutineContextProvider
import com.ofal.ihsan.footballapp.view.activities.detail.team.DetailTeamsView
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class DetailTeamsPresenter(private val view: DetailTeamsView,
                           private val apiRequest: ApiRequest,
                           private val gson: Gson,
                           private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getTeamDetail(idTeam: String?) {
        view.showLoading()

        async(context.main) {
            val eventDetailTeam = bg {
                gson.fromJson(apiRequest.doRequest(TheSportApi.getListDetailTeam(idTeam)),
                        TeamResponse::class.java)
            }
            val listPlayerTeam = bg {
                gson.fromJson(apiRequest.doRequest(TheSportApi.getListPlayerTeam(idTeam)),
                        PlayerResponse::class.java)
            }
            view.showEventList(eventDetailTeam.await().team, listPlayerTeam.await().player)
            view.hideLoading()
        }
    }
}
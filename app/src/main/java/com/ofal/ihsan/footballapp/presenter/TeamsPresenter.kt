package com.ofal.ihsan.footballapp.presenter

import com.google.gson.Gson
import com.ofal.ihsan.footballapp.api.ApiRequest
import com.ofal.ihsan.footballapp.api.TheSportApi
import com.ofal.ihsan.footballapp.model.TeamResponse
import com.ofal.ihsan.footballapp.utils.CoroutineContextProvider
import com.ofal.ihsan.footballapp.view.fragment.teams.TeamsView
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class TeamsPresenter (private val view: TeamsView,
                      private val apiRequest: ApiRequest,
                      private val gson: Gson,
                      private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getTeamList(match: String?) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRequest.doRequest(TheSportApi.getListTeamByLeague(match)),
                        TeamResponse::class.java
                )
            }
            view.showEventList(data.await().team)
            view.hideLoading()
        }
    }

    fun getSearchTeam(team: String?) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRequest.doRequest(TheSportApi.getSearchTeamByName(team)),
                        TeamResponse::class.java
                )
            }
            view.showEventList(data.await().team)
            view.hideLoading()
        }
    }
}
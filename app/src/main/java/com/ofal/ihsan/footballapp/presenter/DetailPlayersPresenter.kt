package com.ofal.ihsan.footballapp.presenter

import com.google.gson.Gson
import com.ofal.ihsan.footballapp.api.ApiRequest
import com.ofal.ihsan.footballapp.api.TheSportApi
import com.ofal.ihsan.footballapp.model.PlayerResponse
import com.ofal.ihsan.footballapp.utils.CoroutineContextProvider
import com.ofal.ihsan.footballapp.view.activities.detail.player.DetailPlayerView
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class DetailPlayersPresenter(private val view: DetailPlayerView,
                             private val apiRequest: ApiRequest,
                             private val gson: Gson,
                             private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getPlayerDetail(idPlayer: String?) {
        view.showLoading()

        async(context.main) {
            val detailPlayerTeam = bg {
                gson.fromJson(apiRequest.doRequest(TheSportApi.getDetailPlayerTeam(idPlayer)),
                        PlayerResponse::class.java)
            }
            view.showPlayerDetail(detailPlayerTeam.await().players)
            view.hideLoading()
        }
    }
}
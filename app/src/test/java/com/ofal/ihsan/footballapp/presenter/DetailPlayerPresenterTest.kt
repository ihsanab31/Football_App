package com.ofal.ihsan.footballapp.presenter

import com.google.gson.Gson
import com.ofal.ihsan.footballapp.TestContextProvider
import com.ofal.ihsan.footballapp.api.ApiRequest
import com.ofal.ihsan.footballapp.api.TheSportApi
import com.ofal.ihsan.footballapp.model.Player
import com.ofal.ihsan.footballapp.model.PlayerResponse
import com.ofal.ihsan.footballapp.view.activities.detail.player.DetailPlayerView
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 085749729115
 * on       : 19, September, 2018
 * ------------------------------
 * This class for detail player
 */
class DetailPlayerPresenterTest {
    @Mock
    private lateinit var view: DetailPlayerView

    @Mock
    private lateinit var gson: Gson

    @Mock
    lateinit var apiRequest: ApiRequest

    private lateinit var presenter: DetailPlayersPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailPlayersPresenter(view, apiRequest, gson, TestContextProvider())
    }

    @Test
    fun getTestDetailPlayers() {
        val detailPlayer: MutableList<Player> = mutableListOf()

        val playerResponse = PlayerResponse(detailPlayer, detailPlayer)

        val idPlayer = "34145411"

        Mockito.`when`(gson.fromJson(apiRequest.doRequest(TheSportApi.getDetailPlayerTeam(idPlayer)),
                PlayerResponse::class.java)).thenReturn(playerResponse)

        presenter.getPlayerDetail(idPlayer)

        Mockito.verify(view).showLoading()
        Mockito.verify(view).showPlayerDetail(detailPlayer)
        Mockito.verify(view).hideLoading()
    }
}
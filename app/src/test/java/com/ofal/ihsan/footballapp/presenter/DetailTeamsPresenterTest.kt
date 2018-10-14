package com.ofal.ihsan.footballapp.presenter

import com.google.gson.Gson
import com.ofal.ihsan.footballapp.TestContextProvider
import com.ofal.ihsan.footballapp.api.ApiRequest
import com.ofal.ihsan.footballapp.api.TheSportApi
import com.ofal.ihsan.footballapp.model.Player
import com.ofal.ihsan.footballapp.model.PlayerResponse
import com.ofal.ihsan.footballapp.model.Team
import com.ofal.ihsan.footballapp.model.TeamResponse
import com.ofal.ihsan.footballapp.view.activities.detail.team.DetailTeamsView
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
 * This class for detail team
 */
class DetailTeamsPresenterTest {
    @Mock
    private lateinit var view: DetailTeamsView

    @Mock
    private lateinit var gson: Gson

    @Mock
    lateinit var apiRequest: ApiRequest

    private lateinit var presenter: DetailTeamsPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailTeamsPresenter(view, apiRequest, gson, TestContextProvider())
    }

    @Test
    fun getTestDetailTeams() {
        val detailTeam: MutableList<Team> = mutableListOf()
        val listPlayer: MutableList<Player> = mutableListOf()

        val response = TeamResponse(detailTeam)
        val homeResponse = PlayerResponse(listPlayer, listPlayer)

        val idTeam = "133604"

        Mockito.`when`(gson.fromJson(apiRequest.doRequest(TheSportApi.getListDetailTeam(idTeam)),
                TeamResponse::class.java)).thenReturn(response)
        Mockito.`when`(gson.fromJson(apiRequest.doRequest(TheSportApi.getListPlayerTeam(idTeam)),
                PlayerResponse::class.java)).thenReturn(homeResponse)

        presenter.getTeamDetail(idTeam)

        Mockito.verify(view).showLoading()
        Mockito.verify(view).showEventList(detailTeam, listPlayer)
        Mockito.verify(view).hideLoading()
    }
}
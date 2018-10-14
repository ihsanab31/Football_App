package com.ofal.ihsan.footballapp.presenter

import com.google.gson.Gson
import com.ofal.ihsan.footballapp.TestContextProvider
import com.ofal.ihsan.footballapp.api.ApiRequest
import com.ofal.ihsan.footballapp.api.TheSportApi
import com.ofal.ihsan.footballapp.model.Team
import com.ofal.ihsan.footballapp.model.TeamResponse
import com.ofal.ihsan.footballapp.view.fragment.teams.TeamsView
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
 * This class for teams
 */
class TeamsPresenterTest  {
    @Mock
    private lateinit var view: TeamsView

    @Mock
    private lateinit var gson: Gson

    @Mock
    lateinit var apiRequest: ApiRequest

    private lateinit var presenter: TeamsPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = TeamsPresenter(view, apiRequest, gson, TestContextProvider())
    }

    @Test
    fun testGetTeams() {
        val teams: MutableList<Team> = mutableListOf()
        val response = TeamResponse(teams)
        val paramEvent = "Spanish"

        Mockito.`when`(gson.fromJson(apiRequest.doRequest(TheSportApi.getListTeamByLeague(paramEvent)),
                TeamResponse::class.java)).thenReturn(response)

        presenter.getTeamList(paramEvent)

        Mockito.verify(view).showLoading()
        Mockito.verify(view).showEventList(teams)
        Mockito.verify(view).hideLoading()
    }
}
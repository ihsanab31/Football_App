package com.ofal.ihsan.footballapp.presenter

import com.google.gson.Gson
import com.ofal.ihsan.footballapp.TestContextProvider
import com.ofal.ihsan.footballapp.api.ApiRequest
import com.ofal.ihsan.footballapp.api.TheSportApi
import com.ofal.ihsan.footballapp.model.Event
import com.ofal.ihsan.footballapp.model.EventResponse
import com.ofal.ihsan.footballapp.model.Team
import com.ofal.ihsan.footballapp.model.TeamResponse
import com.ofal.ihsan.footballapp.view.activities.detail.match.DetailMatchesView
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
 * This class for detail event
 */
class DetailEventPresenterTest {
    @Mock
    private lateinit var view: DetailMatchesView

    @Mock
    private lateinit var gson: Gson

    @Mock
    lateinit var apiRequest: ApiRequest

    private lateinit var presenter: DetailMatchesPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailMatchesPresenter(view, apiRequest, gson, TestContextProvider())
    }

    @Test
    fun testGetNextEvent() {
        val events: MutableList<Event> = mutableListOf()
        val home: MutableList<Team> = mutableListOf()
        val away: MutableList<Team> = mutableListOf()
        val response = EventResponse(events, events)
        val homeResponse = TeamResponse(home)
        val awayResponse = TeamResponse(away)
        val idEvent = "526916"
        val idHomeTeam = "134778"
        val idAwayTeam = "133613"

        Mockito.`when`(gson.fromJson(apiRequest.doRequest(TheSportApi.getDetailEvent(idEvent)),
                EventResponse::class.java)).thenReturn(response)
        Mockito.`when`(gson.fromJson(apiRequest.doRequest(TheSportApi.getHomeBadge(idHomeTeam)),
                TeamResponse::class.java)).thenReturn(homeResponse)
        Mockito.`when`(gson.fromJson(apiRequest.doRequest(TheSportApi.getAwayBadge(idAwayTeam)),
                TeamResponse::class.java)).thenReturn(awayResponse)

        presenter.getEventDetail(idEvent, idHomeTeam, idAwayTeam)

        Mockito.verify(view).showLoading()
        Mockito.verify(view).showEventList(events, home, away)
        Mockito.verify(view).hideLoading()
    }
}
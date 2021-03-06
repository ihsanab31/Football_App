package com.ofal.ihsan.footballapp.presenter

import com.google.gson.Gson
import com.ofal.ihsan.footballapp.TestContextProvider
import com.ofal.ihsan.footballapp.api.ApiRequest
import com.ofal.ihsan.footballapp.api.TheSportApi
import com.ofal.ihsan.footballapp.model.Event
import com.ofal.ihsan.footballapp.model.EventResponse
import com.ofal.ihsan.footballapp.view.fragment.match.next.NextView
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
 * This class for next event
 */
class NextEventPresenterTest {
    @Mock
    private lateinit var view: NextView

    @Mock
    private lateinit var gson: Gson

    @Mock
    lateinit var apiRequest: ApiRequest

    private lateinit var presenter: NextPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = NextPresenter(view, apiRequest, gson, TestContextProvider())
    }

    @Test
    fun testGetNextEvent() {
        val events: MutableList<Event> = mutableListOf()
        val response = EventResponse(events, events)
        val paramEvent = "eventsnextleague"

        Mockito.`when`(gson.fromJson(apiRequest.doRequest(TheSportApi.getSchedule(paramEvent)),
                EventResponse::class.java)).thenReturn(response)

        presenter.getEventList(paramEvent)

        Mockito.verify(view).showLoading()
        Mockito.verify(view).showEventList(events)
        Mockito.verify(view).hideLoading()
    }
}
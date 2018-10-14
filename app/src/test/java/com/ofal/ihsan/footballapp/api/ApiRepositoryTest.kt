package com.ofal.ihsan.footballapp.api

import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.verify

/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 085749729115
 * on       : 19, September, 2018
 * ------------------------------
 * This class for api
 */
class ApiRepositoryTest {
    @Test
    fun testSearchEventByName() {
        val apiRepository = Mockito.mock(ApiRequest::class.java)
        val url = TheSportApi.getSearchEventByName("Chelsea")
        apiRepository.doRequest(url)
        verify(apiRepository).doRequest(url)
    }

    @Test
    fun testSearchTeam() {
        val apiRepository = Mockito.mock(ApiRequest::class.java)
        val url = TheSportApi.getSearchTeamByName("Liverpool")
        apiRepository.doRequest(url)
        verify(apiRepository).doRequest(url)
    }
}
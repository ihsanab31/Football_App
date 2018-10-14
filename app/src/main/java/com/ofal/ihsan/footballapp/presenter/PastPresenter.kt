package com.ofal.ihsan.footballapp.presenter

import com.google.gson.Gson
import com.ofal.ihsan.footballapp.api.ApiRequest
import com.ofal.ihsan.footballapp.api.TheSportApi
import com.ofal.ihsan.footballapp.model.EventResponse
import com.ofal.ihsan.footballapp.utils.CoroutineContextProvider
import com.ofal.ihsan.footballapp.view.fragment.match.prev.PastView
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class PastPresenter (private val view: PastView,
                     private val apiRequest: ApiRequest,
                     private val gson: Gson,
                     private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getEventList(match: String?) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRequest.doRequest(TheSportApi.getSchedule(match)),
                        EventResponse::class.java
                )
            }
            view.showEventList(data.await().event)
            view.hideLoading()
        }
    }

    fun getSearchEventList(match: String?) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRequest.doRequest(TheSportApi.getSearchEventByName(match)),
                        EventResponse::class.java
                )
            }
            view.showEventList(data.await().searchEvent)
            view.hideLoading()
        }
    }

    fun getEventListByLeague(nameLeague: String?) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRequest.doRequest(TheSportApi.getListEventByLeague(nameLeague)),
                        EventResponse::class.java
                )
            }
            view.showEventList(data.await().searchEvent)
            view.hideLoading()
        }
    }
}
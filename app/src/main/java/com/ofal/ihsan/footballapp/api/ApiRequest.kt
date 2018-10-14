package com.ofal.ihsan.footballapp.api

import java.net.URL

/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 085749729115
 * on       : 19, September, 2018
 * ------------------------------
 * This class for api request
 */
class ApiRequest {

    fun doRequest(url: String) : String {
        return URL(url).readText()
    }
}
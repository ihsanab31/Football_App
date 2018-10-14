package com.ofal.ihsan.footballapp.utils

import android.content.Context
import android.view.View
import com.ofal.ihsan.footballapp.model.db.FootballDbHelper

/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 085749729115
 * on       : 19, September, 2018
 * ------------------------------
 * This class for
 */

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

val Context.db: FootballDbHelper
    get() = FootballDbHelper.getInstance(applicationContext)
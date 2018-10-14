package com.ofal.ihsan.footballapp.utils

import kotlinx.coroutines.experimental.android.UI
import kotlin.coroutines.experimental.CoroutineContext


/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 085749729115
 * on       : 19, September, 2018
 * ------------------------------
 * This class for
 */
open class CoroutineContextProvider {
    open val main: CoroutineContext by lazy { UI }
}
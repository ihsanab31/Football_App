package com.ofal.ihsan.footballapp.view.basicui

import android.view.ViewGroup
import android.widget.LinearLayout
import com.ofal.ihsan.footballapp.R
import org.jetbrains.anko.*

/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 085749729115
 * on       : 19, September, 2018
 * ------------------------------
 * This class for basic ui item team
 */
class ItemTeamsUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            padding = dip(16)
            orientation = LinearLayout.HORIZONTAL

            imageView {
                id = R.id.team_badge
            }.lparams {
                height = dip(50)
                width = dip(50)
            }

            textView {
                id = R.id.team_name
                textSize = 16f
            }.lparams {
                margin = dip(15)
            }
        }
    }
}
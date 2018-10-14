package com.ofal.ihsan.footballapp.view.basicui

import android.graphics.Typeface
import android.view.ViewGroup
import android.widget.LinearLayout
import com.ofal.ihsan.footballapp.R
import com.ofal.ihsan.footballapp.R.color.colorWhite
import org.jetbrains.anko.*

/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 085749729115
 * on       : 19, September, 2018
 * ------------------------------
 * This class for basic ui player
 */
class ItemListPlayerUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {

        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL

            relativeLayout {

                backgroundColorResource = colorWhite

                imageView {
                    id = R.id.ivListPlayer
                }.lparams(width = dip(50), height = dip(50)) {
                    alignParentLeft()
                }

                textView {
                    id = R.id.tvListNamePlayer
                    textSize = 16f
                    setTypeface(null, Typeface.BOLD)
                }.lparams(width = wrapContent, height = wrapContent) {
                    centerInParent()
                    leftMargin = dip(8)
                }

                textView {
                    id = R.id.tvPositionListPlayer
                    textSize = 16f
                }.lparams(width = wrapContent, height = wrapContent) {
                    alignParentRight()
                    centerVertically()
                    leftMargin = dip(8)
                }
            }.lparams(width = matchParent, height = wrapContent) {
                margin = dip(8)
            }
        }
    }
}
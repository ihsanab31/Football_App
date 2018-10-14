package com.ofal.ihsan.footballapp.view.basicui

import android.graphics.Typeface
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.ofal.ihsan.footballapp.R
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 085749729115
 * on       : 19, September, 2018
 * ------------------------------
 * This class for basic item match
 */
class ItemMatchesUI : AnkoComponent<ViewGroup> {

    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
        cardView {
            lparams(width = matchParent, height = wrapContent) {
                topMargin = dip(8)
                rightMargin = dip(8)
                leftMargin = dip(8)
                radius = 8f
            }

            linearLayout {
                orientation = LinearLayout.VERTICAL
                padding = dip(16)

                //time event

                textView {
                    id = R.id.time_schedule
                    textSize = 14f
                    gravity = Gravity.CENTER
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                    textColor = R.color.color_title_text
                    setTypeface(null, Typeface.BOLD)
                }.lparams(width = matchParent, height = wrapContent) {
                    bottomMargin = dip(8)
                }

                linearLayout {
                    orientation = LinearLayout.HORIZONTAL
                    weightSum = 1f

                    //Home Team
                    relativeLayout {
                        gravity = Gravity.CENTER

                        textView {
                            id = R.id.tvHomeTeam
                            gravity = Gravity.LEFT
                            textSize = 18f
                            textColor = R.color.color_title_text
                            ellipsize = TextUtils.TruncateAt.END
                            maxLines = 1
                        }.lparams(width = wrapContent, height = wrapContent)

                    }.lparams(width = dip(0), height = wrapContent, weight = 0.35f)

                    //Score

                    linearLayout {
                        orientation = LinearLayout.HORIZONTAL
                        gravity = Gravity.CENTER

                        textView {
                            id = R.id.tvHomeScore
                            textSize = 18f
                            gravity = Gravity.CENTER
                            setTypeface(null, Typeface.BOLD)
                        }.lparams(width = wrapContent, height = wrapContent) {
                            rightMargin = dip(8)
                        }

                        textView {
                            id = R.id.tvVersus
                            gravity = Gravity.CENTER
                            textSize = 16f
                            text = context.getString(R.string.resource_vs)
                        }.lparams(width = wrapContent, height = wrapContent)

                        textView {
                            id = R.id.tvAwayScore
                            gravity = Gravity.CENTER
                            textSize = 18f
                            setTypeface(null, Typeface.BOLD)
                        }.lparams(width = wrapContent, height = wrapContent) {
                            leftMargin = dip(8)
                        }
                    }.lparams(width = dip(0), height = wrapContent, weight = 0.3f) {
                        leftMargin = dip(8)
                        rightMargin = dip(8)
                    }

                    //Away Team

                    linearLayout {
                        gravity = Gravity.CENTER

                        textView {
                            id = R.id.tvAwayTeam
                            gravity = Gravity.RIGHT
                            textSize = 18f
                            textColor = R.color.color_title_text
                            ellipsize = TextUtils.TruncateAt.END
                            maxLines = 1
                        }.lparams(width = wrapContent, height = wrapContent)

                    }.lparams(width = dip(0), height = wrapContent, weight = 0.35f)

                }.lparams(width = matchParent, height = wrapContent) {
                    leftMargin = dip(8)
                    rightMargin = dip(8)
                }
            }.lparams(width = matchParent, height = wrapContent)
        }
    }
}
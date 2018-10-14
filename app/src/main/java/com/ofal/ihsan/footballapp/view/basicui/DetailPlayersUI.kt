package com.ofal.ihsan.footballapp.view.basicui

import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import com.ofal.ihsan.footballapp.R
import com.ofal.ihsan.footballapp.view.activities.detail.player.DetailPlayersActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.swipeRefreshLayout

/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 085749729115
 * on       : 19, September, 2018
 * ------------------------------
 * This class for basic ui player
 */
class DetailPlayersUI : AnkoComponent<DetailPlayersActivity> {

    override fun createView(ui: AnkoContext<DetailPlayersActivity>) = with(ui) {
        swipeRefreshLayout {
            id = R.id.swipeRefreshPlayerDetail
            setColorSchemeResources(R.color.colorAccent,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light)

            scrollView {
                lparams(width = matchParent, height = matchParent)
                isFillViewport = true

                relativeLayout {
                    lparams(width = matchParent, height = matchParent)

                    linearLayout {
                        lparams(width = matchParent, height = matchParent)
                        orientation = LinearLayout.VERTICAL
                        id = R.id.lyPlayerDetail
                        backgroundColorResource = R.color.colorWhite

                        imageView {
                            id = R.id.bgPlayerDetail
                        }.lparams(width = matchParent, height = wrapContent)

                        linearLayout {
                            orientation = LinearLayout.HORIZONTAL
                            weightSum = 1f

                            linearLayout {
                                orientation = LinearLayout.VERTICAL
                                gravity = Gravity.CENTER

                                textView {
                                    text = context.getString(R.string.example_weight)
                                    textColor = R.color.colorDividerItem
                                    textSize = 18f
                                }.lparams(width = wrapContent, height = wrapContent)

                                textView {
                                    id = R.id.tvWeightPlayer
                                    textColor = R.color.colorPrimaryDark
                                    textSize = 36f
                                }.lparams(width = wrapContent, height = wrapContent) {
                                    topMargin = dip(8)
                                }

                            }.lparams(width = dip(0), height = wrapContent, weight = 0.5f)

                            linearLayout {
                                orientation = LinearLayout.VERTICAL
                                gravity = Gravity.CENTER

                                textView {
                                    text = context.getString(R.string.example_height)
                                    textColor = R.color.colorDividerItem
                                    textSize = 18f
                                }.lparams(width = wrapContent, height = wrapContent)

                                textView {
                                    id = R.id.tvHeightPlayer
                                    textColor = R.color.colorPrimaryDark
                                    textSize = 36f
                                    lines = 1
                                    ellipsize = TextUtils.TruncateAt.END
                                }.lparams(width = wrapContent, height = wrapContent) {
                                    topMargin = dip(8)
                                }

                            }.lparams(width = dip(0), height = wrapContent, weight = 0.5f)

                        }.lparams(width = matchParent, height = wrapContent)

                        textView {
                            id = R.id.tvForwardPlayer
                            textSize = 16f
                            textColor = R.color.colorPrimaryDark
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                        }.lparams(width = matchParent, height = wrapContent) {
                            topMargin = dip(16)
                        }

                        view {
                            backgroundColor = R.color.colorDividerItem
                        }.lparams(width = matchParent, height = dip(1))

                        textView {
                            id = R.id.tvDescriptionPlayerDetail
                            textSize = 12f
                        }.lparams(width = wrapContent, height = wrapContent) {
                            margin = dip(8)
                        }
                    }

                    progressBar {
                        id = R.id.pbDetailPlayer
                    }.lparams {
                        centerInParent()
                    }
                }
            }
        }
    }
}
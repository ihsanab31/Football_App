package com.ofal.ihsan.footballapp.view.basicui

import android.graphics.Typeface
import android.view.Gravity
import android.widget.LinearLayout
import com.ofal.ihsan.footballapp.R
import com.ofal.ihsan.footballapp.R.color.colorPrimary
import com.ofal.ihsan.footballapp.R.color.colorWhite
import com.ofal.ihsan.footballapp.view.activities.detail.team.DetailTeamsActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.swipeRefreshLayout

/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 085749729115
 * on       : 19, September, 2018
 * ------------------------------
 * This class for
 */
class DetailTeamsUI : AnkoComponent<DetailTeamsActivity> {
    override fun createView(ui: AnkoContext<DetailTeamsActivity>) = with(ui) {

        swipeRefreshLayout {
            id = R.id.swipeRefreshTeamDetail
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
                        id = R.id.lyTeamDetail

                        linearLayout {
                            orientation = LinearLayout.VERTICAL
                            gravity = Gravity.CENTER
                            backgroundColorResource = colorPrimary

                            imageView {
                                id = R.id.ivBadgeTeamDetail
                            }.lparams(width = dip(100), height = dip(100)) {
                                gravity = Gravity.CENTER_HORIZONTAL
                                topMargin = dip(16)
                            }

                            textView {
                                id = R.id.tvTitleDetailTeam
                                textSize = 18f
                                gravity = Gravity.CENTER
                                setTypeface(null, Typeface.BOLD)
                                textColorResource = colorWhite
                            }.lparams(width = wrapContent, height = wrapContent) {
                                horizontalGravity = Gravity.CENTER
                                topMargin = dip(8)
                            }

                            textView {
                                id = R.id.tvYearDetailTeam
                                textSize = 14f
                                gravity = Gravity.CENTER
                                textColorResource = colorWhite
                            }.lparams(width = wrapContent, height = wrapContent) {
                                horizontalGravity = Gravity.CENTER
                                topMargin = dip(8)
                            }

                            textView {
                                id = R.id.tvStadiumDetailTeam
                                textSize = 14f
                                gravity = Gravity.CENTER
                                textColorResource = colorWhite
                            }.lparams(width = wrapContent, height = wrapContent) {
                                horizontalGravity = Gravity.CENTER
                                topMargin = dip(8)
                                bottomMargin = dip(8)
                            }
                        }.lparams(width = matchParent, height = wrapContent)

                        linearLayout {
                            lparams(width = matchParent, height = wrapContent)
                            orientation = LinearLayout.VERTICAL
                            backgroundColorResource = colorWhite

                            textView {
                                text = context.getString(R.string.title_detail_team_overview)
                                textSize = 16f
                                setTypeface(null, Typeface.BOLD)
                                gravity = Gravity.LEFT
                            }.lparams(width = wrapContent, height = wrapContent) {
                                topMargin = dip(16)
                                leftMargin = dip(8)
                            }

                            view {
                                backgroundColor = R.color.colorDividerItem
                            }.lparams(width = matchParent, height = dip(1))

                            textView {
                                id = R.id.tvDescriptionTeamDetail
                                textSize = 12f
                            }.lparams(width = wrapContent, height = wrapContent) {
                                margin = dip(8)
                            }

                            textView {
                                text = context.getString(R.string.player)
                                textSize = 16f
                                setTypeface(null, Typeface.BOLD)
                                gravity = Gravity.LEFT
                            }.lparams(width = wrapContent, height = wrapContent) {
                                topMargin = dip(16)
                                leftMargin = dip(8)
                            }

                            view {
                                backgroundColor = R.color.colorDividerItem
                            }.lparams(width = matchParent, height = dip(1))

                            recyclerView {
                                id = R.id.rvListPlayerTeam
                            }.lparams(width = matchParent, height = matchParent) {
                                gravity = Gravity.CENTER
                            }
                        }
                    }

                    progressBar {
                        id = R.id.pbDetailTeam
                    }.lparams {
                        centerInParent()
                    }
                }
            }
        }
    }
}
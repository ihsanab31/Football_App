package com.ofal.ihsan.footballapp.model.db

/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 085749729115
 * on       : 19, September, 2018
 * ------------------------------
 * This class for field table team
 */
class TeamDB (val id: Long?, val teamId: String?, val teamName: String?,
              val badgeTeam: String?) {

    companion object {
        const val TABLE_TEAMS = "TABLE_TEAMS"
        const val ID: String = "ID_"
        const val TEAMS_ID: String = "TEAMS_ID"
        const val TEAMS_NAME: String = "TEAMS_NAME"
        const val BADGE_TEAM: String = "BADGE_TEAM"
    }
}
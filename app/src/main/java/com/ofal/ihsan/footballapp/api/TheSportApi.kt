package com.ofal.ihsan.footballapp.api

import com.ofal.ihsan.footballapp.BuildConfig

/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 085749729115
 * on       : 19, September, 2018
 * ------------------------------
 * This class for api db sport
 */
object TheSportApi {
    private const val eventPastLeague = "eventspastleague.php?id="
    private const val eventNextLeague = "eventnextleague.php?id="
    private const val strLookUpEvent = "lookupevent.php?id="
    private const val strLookUpTeam = "lookupteam.php?id="
    private const val strLookUpPlayer = "lookup_all_players.php?id="
    private const val strLookUpDetailPlayer = "lookupplayer.php?id="
    private const val strListTeamByNameLeague = "search_all_teams.php?l="
    private const val strListLeagueByName = "searchfilename.php?e="
    private const val strSearchEventByName = "searchevents.php?e="
    private const val strSearchTeamByName = "searchteams.php?t="
    private const val idLeague = ".php?id=4328"

    fun getSchedule(eventLeague: String?): String {
        return BuildConfig.BASE_URL + eventLeague + idLeague
    }

    fun getPastEvent(idEvent: String?) : String {
        return BuildConfig.BASE_URL + eventPastLeague + idEvent
    }

    fun getNextEvent(idEvent: String?) : String {
        return BuildConfig.BASE_URL + eventNextLeague + idEvent
    }

    fun getDetailEvent(idDetail: String?) : String {
        return BuildConfig.BASE_URL + strLookUpEvent + idDetail
    }

    fun getHomeBadge(idHome: String?) : String {
        return BuildConfig.BASE_URL + strLookUpTeam + idHome
    }

    fun getAwayBadge(idAway: String?) : String {
        return BuildConfig.BASE_URL + strLookUpTeam + idAway
    }

    fun getListTeamByLeague(nameLeague: String?) : String {
        return BuildConfig.BASE_URL + strListTeamByNameLeague + nameLeague
    }

    fun getListDetailTeam(idTeam: String?) : String {
        return BuildConfig.BASE_URL + strLookUpTeam + idTeam
    }

    fun getListPlayerTeam(nameTeam: String?) : String {
        return BuildConfig.BASE_URL + strLookUpPlayer + nameTeam
    }

    fun getListEventByLeague(nameLeague: String?) : String {
        return BuildConfig.BASE_URL + strListLeagueByName + nameLeague
    }

    fun getDetailPlayerTeam(idPlayer: String?) : String {
        return BuildConfig.BASE_URL + strLookUpDetailPlayer + idPlayer
    }

    fun getSearchEventByName(eventName: String?) : String {
        return BuildConfig.BASE_URL + strSearchEventByName + eventName
    }

    fun getSearchTeamByName(teamName: String?) : String {
        return BuildConfig.BASE_URL + strSearchTeamByName + teamName
    }
}
package com.imsaku.summonersinfo.model

import com.squareup.moshi.Json

data class Summoner(
    @Json(name = "accountId") val accountId: String,
    @Json(name = "profileIconId") val profileIconId: Int,
    @Json(name = "revisionDate") val revisionDate: Long,
    @Json(name = "name") val name: String,
    @Json(name = "id") val id: String,
    @Json(name = "puuid") val puuid: String,
    @Json(name = "summonerLevel") val summonerLevel: Long
)
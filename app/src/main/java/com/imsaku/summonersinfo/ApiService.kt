package com.imsaku.summonersinfo

import com.imsaku.summonersinfo.model.Summoner
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

// TODO: RIOT GAMES API KEY
private const val API_KEY = "YOUR API KEY"
// Platform Server : JP
private const val BASE_URL = "https://jp1.api.riotgames.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface ApiService {
    @GET("lol/summoner/v4/summoners/by-name/{name}?api_key=${API_KEY}")
    fun getSummoner(@Path("name") summonerName: String): Deferred<Summoner>
}

object Api {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
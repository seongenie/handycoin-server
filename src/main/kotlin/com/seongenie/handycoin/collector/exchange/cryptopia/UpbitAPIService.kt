package com.seongenie.handycoin.collector.exchange.cryptopia

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UpbitAPIService {
    @GET("market/all")
    fun getMarkets(): Call<List<UpbitMarket>>


    @GET("ticker")
    fun getTickers(@Query("markets") markets: String): Call<List<UpbitTicker>>
}
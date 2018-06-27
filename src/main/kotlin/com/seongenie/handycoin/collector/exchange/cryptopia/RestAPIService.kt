package com.seongenie.handycoin.collector.exchange.cryptopia

import retrofit2.Call
import retrofit2.http.GET

interface RestAPIService {
    @GET("GetMarkets")
    fun getMarkets() : Call<ResultView<Market>>

    @GET("GetCurrencies")
    fun getCurrencies() : Call<ResultView<Currency>>
}


//https://www.cryptopia.co.nz/api/GetMarkets
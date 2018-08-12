package com.seongenie.handycoin.collector.exchange.cryptopia

import com.seongenie.handycoin.collector.infra.CollectorModule
import com.seongenie.handycoin.controller.baseCoin.BaseCoinView
import com.seongenie.handycoin.service.BaseCoinService
import com.seongenie.handycoin.service.CoinTickerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Component
class Cryptopia {

    @Autowired
    lateinit var baseCoinService: BaseCoinService
    @Autowired
    lateinit var coinTickerService: CoinTickerService
    var retrofit : Retrofit? = null
    var apiService : RestAPIService? = null

    fun buildApiService() : Cryptopia{
        retrofit = Retrofit.Builder()
                .baseUrl("https://www.cryptopia.co.nz/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        apiService = retrofit?.create(RestAPIService::class.java)
        return this
    }

    fun marketProcess() {
        if(apiService == null) return
        var request : Call<ResultView<Market>> = apiService!!.getMarkets()
        request.enqueue(CollectorModule.callbackWrapper({ response ->
            if(response.body()?.success!!) {
                var marketList : List<Market>? = response.body()!!.data
                marketList?.forEach{
                    val splited = it.label.split("/")
                    if (splited[1].equals("BTC")) {
                        try {
                            baseCoinService.createBaseCoin(BaseCoinView("CRYPTOPIA", splited[0], splited[1]))
                        } catch (e : Exception) {
                            e.printStackTrace()
                        }
                    }
                }
            }
        }, {t->t.printStackTrace()}))
    }

    fun tickerProcess() {
        if(apiService == null) return
        var request : Call<ResultView<Market>> = apiService!!.getMarkets()
        request.enqueue(CollectorModule.callbackWrapper({ response ->
            if (response.body()?.success!!) {
                var marketList : List<Market>? = response.body()!!.data
                marketList?.forEach{
                    if (it.label.split("/")[1].equals("BTC")) {
                        try {
                            coinTickerService.insertCoinTicker(it)
                        } catch (e : Exception) {
                            e.printStackTrace()
                        }
                    }
                }
            }
        }, {t->t.printStackTrace()}))
    }

}
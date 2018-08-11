package com.seongenie.handycoin.collector.exchange.cryptopia

import com.seongenie.handycoin.collector.infra.CollectorModule
import com.seongenie.handycoin.controller.baseCoin.BaseCoinView
import com.seongenie.handycoin.service.BaseCoinService
import com.seongenie.handycoin.service.CoinPriceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Component
@SuppressWarnings("all")
class Upbit {

    @Autowired
    lateinit var baseCoinService: BaseCoinService
    @Autowired
    lateinit var coinPriceService: CoinPriceService
    var retrofit : Retrofit? = null
    var apiService : UpbitAPIService? = null

    fun buildApiService() : Upbit {
        retrofit = Retrofit.Builder()
                .baseUrl("https://api.upbit.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        apiService = retrofit?.create(UpbitAPIService::class.java)
        return this
    }

    fun marketProcess() {
        if(apiService == null) return
        var request : Call<List<UpbitMarket>> = apiService!!.getMarkets()
        request.enqueue(CollectorModule.callbackWrapper({ response ->
            val list: List<UpbitMarket>? = response.body()
            list?.forEach { it ->
                val currency = it.market.split("-")
                if (currency[0].equals("KRW")) {
                    baseCoinService.createBaseCoin(BaseCoinView(exchange = "UPBIT", coin = currency[1], currency = currency[0]))
                }
            }
        }))
    }


    fun tickerProcess(markets : String) {
        if(apiService == null) return
        var request : Call<List<UpbitTicker>> = apiService!!.getTickers(markets)
        var count = 0
        request.enqueue(CollectorModule.callbackWrapper({ response ->
            val ticker : List<UpbitTicker>? = response.body()
            ticker?.forEach { it ->
                coinPriceService.updateCoinPrice(it)
                count++ }
            println("${count}개 업데이트")
        }))
    }


}
package com.seongenie.handycoin.collector.exchange.cryptopia

import com.seongenie.handycoin.controller.basicCoin.BasicCoinView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.seongenie.handycoin.service.BasicCoinService
//import com.seongenie.handycoin.service.CoinPriceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class Cryptopia {

    @Autowired
    lateinit var basicCoinService: BasicCoinService
//    @Autowired
//    lateinit var coinPriceService: CoinPriceService
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
        request.enqueue(object : Callback<ResultView<Market>> {
            override fun onFailure(call: Call<ResultView<Market>>?, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<ResultView<Market>>?, response: Response<ResultView<Market>>?) {
                if (response?.body()?.success == true) {
                    var marketList : List<Market>? = response.body()!!.data
                    marketList?.forEach({
                        if (it.label.split("/")[1].equals("BTC")) {
                            try {
                                basicCoinService.createBasicCoin(BasicCoinView("CRYPTOPIA", it.label.split("/")[0]))
                            } catch (e : Exception) {
                                e.printStackTrace()
                            }
                        }
                    })
                }
            }
        })
    }

//    fun priceProcess() {
//        if(apiService == null) return
//        var request : Call<ResultView<Market>> = apiService!!.getMarkets()
//        request.enqueue(object : Callback<ResultView<Market>> {
//            override fun onFailure(call: Call<ResultView<Market>>?, t: Throwable) {
//                t.printStackTrace()
//            }
//
//            override fun onResponse(call: Call<ResultView<Market>>?, response: Response<ResultView<Market>>?) {
//                if (response?.body()?.success == true) {
//                    var marketList : List<Market>? = response.body()!!.data
//                    marketList?.forEach({
//                        if (it.label.split("/")[1].equals("BTC")) {
//                            try {
//                                coinPriceService.insertCoinPrice(it)
//                            } catch (e : Exception) {
//                                e.printStackTrace()
//                            }
//                        }
//                    })
//                }
//            }
//        })
//    }

}
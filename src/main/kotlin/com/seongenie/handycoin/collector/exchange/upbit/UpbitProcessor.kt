package com.seongenie.handycoin.collector.exchange.upbit

import com.seongenie.handycoin.collector.exchange.infra.BaseAPIProcessor
import com.seongenie.handycoin.controller.baseCoin.BaseCoinView
import com.seongenie.handycoin.service.BaseCoinService
import com.seongenie.handycoin.service.CoinTickerService
import io.reactivex.schedulers.Schedulers
import org.springframework.beans.factory.annotation.Autowired

class UpbitProcessor : BaseAPIProcessor {
  lateinit var apiService: UpbitAPIService

  @Autowired
  lateinit var baseCoinService: BaseCoinService
  @Autowired
  lateinit var coinTickerService: CoinTickerService

  constructor(apiService: UpbitAPIService) : super() {
    this.apiService = apiService
  }

  override fun marketProcess() {
    apiService.getMarkets().subscribe({
      it.forEach { it ->
        val currency = it.market.split("-")
        if (currency[0].equals("KRW")) {
          baseCoinService.createBaseCoin(BaseCoinView(exchange = "UPBIT", coin = currency[1], currency = currency[0]))
        }
      }
    }, { t -> t.printStackTrace() })
  }

  override fun tickerProcess(markets: String) {
    apiService.getTickers(markets).subscribe({
      it.forEach { coinTickerService.updateCoinTicker(it) }
      println("${it.size} tickers Update!!")
    }, { t -> t.printStackTrace() })
  }

}
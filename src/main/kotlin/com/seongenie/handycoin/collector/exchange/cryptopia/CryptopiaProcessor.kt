package com.seongenie.handycoin.collector.exchange.cryptopia

import com.seongenie.handycoin.controller.baseCoin.BaseCoinView
import com.seongenie.handycoin.infra.BaseException
import com.seongenie.handycoin.service.BaseCoinService
import com.seongenie.handycoin.service.CoinTickerService
import org.springframework.beans.factory.annotation.Autowired

class CryptopiaProcessor<out BaseAPIService> {
  @Autowired
  lateinit var baseCoinService: BaseCoinService

  @Autowired
  lateinit var coinTickerService: CoinTickerService
  lateinit var apiService: CryptopiaAPIService

  constructor(apiService: CryptopiaAPIService) : super() {
    this.apiService = apiService
  }

  fun marketProcess() {
    apiService.getMarkets().subscribe({
      if (it.success) {
        it.data.forEach {
          val splitted = it.label.split("/")
          if (splitted[1].equals("BTC")) {
            baseCoinService.createBaseCoin(BaseCoinView("CRYPTOPIA", splitted[0], splitted[1]))
          }
        }
        return@subscribe
      }
      throw BaseException()
    }, { t -> t.printStackTrace()})
  }

  fun tickerProcess() {
    apiService.getMarkets().subscribe({
      if (it.success) {
        it.data.forEach {
          val splitted = it.label.split("/")
          if (splitted[1].equals("BTC")) {
            coinTickerService.insertCoinTicker(it)
          }
        }
        return@subscribe
      }
      throw BaseException()
    }, { t -> t.printStackTrace()})
  }

}
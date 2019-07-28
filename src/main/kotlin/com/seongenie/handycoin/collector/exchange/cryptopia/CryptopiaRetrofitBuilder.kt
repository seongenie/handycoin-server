package com.seongenie.handycoin.collector.exchange.cryptopia

import com.seongenie.handycoin.collector.exchange.infra.BaseAPIService
import com.seongenie.handycoin.collector.exchange.infra.BaseRetrofitBuilder

class CryptopiaRetrofitBuilder : BaseRetrofitBuilder() {
  override var baseUrl: String = "https://www.cryptopia.co.nz/api/"
  override var apiService: Class<out BaseAPIService> = CryptopiaAPIService::class.java
}
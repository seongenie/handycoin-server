package com.seongenie.handycoin.collector.exchange.upbit

import com.seongenie.handycoin.collector.exchange.infra.BaseAPIService
import com.seongenie.handycoin.collector.exchange.infra.BaseRetrofitBuilder

/**
 * Api docs -> https://docs.upbit.com/docs/upbit-quotation-restful-api
 */
class UpbitRetrofitBuilder : BaseRetrofitBuilder() {
  override var baseUrl: String = "https://api.upbit.com/v1/"
  override var apiService: Class<out BaseAPIService> = UpbitAPIService::class.java

}
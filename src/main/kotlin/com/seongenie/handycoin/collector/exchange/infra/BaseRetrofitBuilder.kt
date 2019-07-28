package com.seongenie.handycoin.collector.exchange.infra

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * BaseRetrofitBuilder
 */
abstract class BaseRetrofitBuilder {

  lateinit var builder: Retrofit.Builder
  abstract var baseUrl: String
  abstract var apiService: Class<out BaseAPIService>

  private fun preBuild(): Retrofit.Builder {
    return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
  }

  fun build(): BaseAPIService {
    return preBuild().baseUrl(baseUrl)
            .build()
            .create(apiService)
  }


}
package com.seongenie.handycoin.collector.exchange.infra

abstract class BaseAPIProcessor {
  abstract fun marketProcess()
  abstract fun tickerProcess(markets: String)
}
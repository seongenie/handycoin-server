package com.seongenie.handycoin.service

import com.seongenie.handycoin.collector.exchange.cryptopia.Market
import com.seongenie.handycoin.collector.exchange.cryptopia.UpbitTicker
import com.seongenie.handycoin.controller.favorCoin.FavorCoinView
import com.seongenie.handycoin.domain.BaseCoin
import com.seongenie.handycoin.domain.BaseCoinRepository
import com.seongenie.handycoin.domain.CoinPriceRepository
import com.seongenie.handycoin.domain.CoinTicker
import com.seongenie.handycoin.domain.infra.BaseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CoinPriceService : BaseService() {

    @Autowired
    lateinit var coinPriceRepository : CoinPriceRepository

    @Autowired
    lateinit var baseCoinRepository : BaseCoinRepository

    fun getBaseCoinList() : List<FavorCoinView> {
        var baseCoinList : List<CoinTicker> = coinPriceRepository.findAll()
        return baseCoinList.map { it -> FavorCoinView(it) }
    }

    fun getCoinPrice(baseCoin: BaseCoin, currency : String) : FavorCoinView? {
        var coinPrice = coinPriceRepository.findCoinPrice(CoinTicker(coin = baseCoin))
        return if(coinPrice != null) FavorCoinView(coinPrice) else null
    }

    fun insertCoinPrice(market : Market) {
        val splited = market.label.split("/")
        val coin = splited[0]
        val currency = splited[1]
        val baseCoin = baseCoinRepository.findBaseCoin(BaseCoin("CRYPTOPIA", coin, currency))
        var coinPrice = CoinTicker(baseCoin!!, lastPrice = market.lastPrice!!)
        coinPriceRepository.add(coinPrice)
    }

    fun updateCoinPrice(ticker : UpbitTicker) {
        val splitTicker = ticker.market.split("-")
        val coin = splitTicker[1]
        val currency = splitTicker[0]
        val baseCoin = baseCoinRepository.findBaseCoin(BaseCoin("UPBIT", coin, currency))
        var coinTicker : CoinTicker = CoinTicker(
                coin = baseCoin!!,
                lastPrice = ticker.tradePrice,
                prevPrice = ticker.prevClosingPrice,
                highPrice = ticker.highPrice,
                lowPrice = ticker.lowPrice,
                volume = ticker.tradeVolume24h )

        var origin = coinPriceRepository.findCoinPrice(coinTicker)
        when(origin) {
            null -> coinPriceRepository.add(coinTicker)
            else -> {
                origin.volume = coinTicker.volume
                origin.prevPrice = coinTicker.prevPrice
                origin.lowPrice = coinTicker.lowPrice
                origin.highPrice = coinTicker.highPrice
                origin.lastPrice = coinTicker.lastPrice
                coinPriceRepository.update(origin)
            }
        }
    }


    fun getFavors(favorMap : Map<String, List<String>>) : List<CoinTicker> {
        val favors : ArrayList<CoinTicker> = arrayListOf()
        favorMap.forEach { it ->
            val coinTickerList:  List<CoinTicker> = coinPriceRepository.findByExchangeCoins(it.key, it.value)
            favors.addAll(coinTickerList)
        }
        return favors
    }
}
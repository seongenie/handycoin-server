package com.seongenie.handycoin.service

import com.seongenie.handycoin.collector.exchange.cryptopia.Cryptopia.basicCoinService
import com.seongenie.handycoin.collector.exchange.cryptopia.Market
import com.seongenie.handycoin.controller.favorCoin.FavorCoinView
import com.seongenie.handycoin.domain.BasicCoin
import com.seongenie.handycoin.domain.BasicCoinRepository
import com.seongenie.handycoin.domain.CoinPriceRepository
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CoinPriceService {

    @Autowired
    lateinit var coinPriceRepository : CoinPriceRepository
    @Autowired
    lateinit var basicCoinRepository : BasicCoinRepository

//    fun getBasicCoinList() : List<BasicCoinView> {
//        var basicCoinList : List<BasicCoin> = repository.findAll()
//        return basicCoinList.map { basicCoin -> BasicCoinView(basicCoin) }
//    }

    fun getCoinPrice(basicCoin: BasicCoin, currency : String) : FavorCoinView {
        val basicCoin = basicCoinRepository.findBasicCoin(basicCoin.exchange, basicCoin.coin)
        var coinPrice = coinPriceRepository.findCoinPriceOne(basicCoin?.id, currency)
        return FavorCoinView(coinPrice?.get(0)!!)
    }

    fun insertCoinPrice(market : Market) {
        val coin = market.label.split("/")[0]
        val basicCoin = basicCoinRepository.findBasicCoin("CRYPTOPIA", coin)
        coinPriceRepository.insertCoinPriceOne(basicCoin?.id!!, market.lastPrice!!, "BTC")
    }

}
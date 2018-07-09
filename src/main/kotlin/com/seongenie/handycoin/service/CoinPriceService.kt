package com.seongenie.handycoin.service

import com.seongenie.handycoin.collector.exchange.cryptopia.Market
import com.seongenie.handycoin.controller.basicCoin.BasicCoinView
import com.seongenie.handycoin.controller.favorCoin.FavorCoinView
import com.seongenie.handycoin.domain.BasicCoin
import com.seongenie.handycoin.domain.BasicCoinRepository
import com.seongenie.handycoin.domain.CoinPrice
import com.seongenie.handycoin.domain.CoinPriceRepository
import com.seongenie.handycoin.domain.infra.BasicService
import com.seongenie.handycoin.domain.infra.HibernateUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.persistence.EntityManager

@Service
class CoinPriceService : BasicService {

    @Autowired
    lateinit var coinPriceRepository : CoinPriceRepository
    @Autowired
    lateinit var basicCoinRepository : BasicCoinRepository

    fun getBasicCoinList() : List<FavorCoinView> {
        var basicCoinList : List<CoinPrice> = coinPriceRepository.findAll()
        return basicCoinList.map { it -> FavorCoinView(it) }
    }

    fun getCoinPrice(basicCoin: BasicCoin, currency : String) : FavorCoinView {
        var coinPrice = coinPriceRepository.findCoinPrice(CoinPrice(coin = basicCoin, currency = currency))
        return FavorCoinView(coinPrice)
    }

    fun insertCoinPrice(market : Market) {
        val coin = market.label.split("/")[0]
        val basicCoin = basicCoinRepository.findBasicCoin(BasicCoin("CRYPTOPIA", coin))
        var coinPrice : CoinPrice = CoinPrice(basicCoin!!, lastPrice = market.lastPrice!!, currency = "BTC")
        coinPriceRepository.insertCoinPriceOne(coinPrice)
    }

}
package com.seongenie.handycoin.controller.favorCoin

import com.seongenie.handycoin.domain.BaseCoin
import com.seongenie.handycoin.domain.CoinTicker
import com.seongenie.handycoin.service.CoinPriceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/v1/favor")
class FavorCntroller {

    @Autowired
    lateinit var coinPriceService : CoinPriceService

    @RequestMapping(value = "/get/{exchange}/{coin}/{currency}", method = [RequestMethod.GET])
    fun getFavorCoins(@PathVariable("exchange") exchange : String, @PathVariable("coin") coin : String, @PathVariable("currency") currency : String) : FavorCoinView? {
        var coin = BaseCoin(exchange, coin, currency)
        return coinPriceService.getCoinPrice(coin, currency)
    }


    @RequestMapping(value = "/getList", method = [RequestMethod.GET])
    fun getFavorCoins(@RequestParam("favor") favorMap : Map<String, List<String>>) : List<CoinTicker>{
//        val map = HashMap<String, List<String>>()
//        map.put("UPBIT", listOf("BTC", "ETH"))
        return coinPriceService.getFavors(favorMap)
    }
}
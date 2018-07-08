//package com.seongenie.handycoin.controller.favorCoin
//
//import com.seongenie.handycoin.domain.BasicCoin
//import com.seongenie.handycoin.domain.BasicCoinRepository
//import com.seongenie.handycoin.service.CoinPriceService
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.web.bind.annotation.PathVariable
//import org.springframework.web.bind.annotation.RequestMapping
//import org.springframework.web.bind.annotation.RequestMethod
//import org.springframework.web.bind.annotation.RestController
//
//
////@RestController
////@RequestMapping("/favorCoin")
//class FavorCntroller {
//
//    @Autowired
//    lateinit var coinPriceService : CoinPriceService
//
////    @RequestMapping(value = "/get/{exchange}/{coin}/{currency}", method = [RequestMethod.GET])
////    fun getFavorCoins(@PathVariable("exchange") exchange : String, @PathVariable("coin") coin : String, @PathVariable("currency") currency : String) : FavorCoinView? {
////        var coin = BasicCoin(exchange, coin)
////        return null
////        return coinPriceService.getCoinPrice(coin, currency)
////    }
//}
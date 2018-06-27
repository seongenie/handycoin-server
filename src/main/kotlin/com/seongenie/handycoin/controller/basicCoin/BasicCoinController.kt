package com.seongenie.handycoin.controller.basicCoin

import com.seongenie.handycoin.domain.BasicCoin
import com.seongenie.handycoin.domain.BasicCoinRepository
import com.seongenie.handycoin.service.BasicCoinService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/basic")
class BasicCoinController {

    @Autowired
    lateinit var basicCoinService : BasicCoinService

    @RequestMapping(value = "/create/{exchange}/{coin}", method = [RequestMethod.POST])
    fun createFavorCoins(@PathVariable("exchange") exchange : String, @PathVariable("coin") coin : String) : BasicCoinView {
        var coinView = BasicCoinView(exchange, coin)
        return basicCoinService.createBasicCoin(coinView)
    }

    @RequestMapping(value = "/getBasicCoins", method = [RequestMethod.GET])
    fun getBasicCoinList() : List<BasicCoinView> {
        return basicCoinService.getBasicCoinList()
    }

}
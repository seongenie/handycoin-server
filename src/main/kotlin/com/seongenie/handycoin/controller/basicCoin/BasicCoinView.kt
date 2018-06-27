package com.seongenie.handycoin.controller.basicCoin

import com.seongenie.handycoin.domain.BasicCoin
import org.springframework.beans.BeanUtils

class BasicCoinView {
    lateinit var exchange : String
    lateinit var coin : String

    constructor(basicCoin : BasicCoin) {
        BeanUtils.copyProperties(basicCoin, this)
    }

    constructor(exchange : String, coin : String) {
        this.exchange = exchange
        this.coin = coin
    }
}
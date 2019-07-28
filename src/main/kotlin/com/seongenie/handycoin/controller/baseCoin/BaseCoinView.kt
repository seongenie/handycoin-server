package com.seongenie.handycoin.controller.baseCoin

import com.seongenie.handycoin.model.BaseCoin
import org.springframework.beans.BeanUtils

class BaseCoinView {
    lateinit var exchange : String
    lateinit var coin : String
    lateinit var currency : String

    constructor(baseCoin : BaseCoin) {
        BeanUtils.copyProperties(baseCoin, this)
    }

    constructor(exchange : String, coin : String, currency : String) {
        this.exchange = exchange
        this.coin = coin
        this.currency = currency
    }
}
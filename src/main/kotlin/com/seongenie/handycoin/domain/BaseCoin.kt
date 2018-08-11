package com.seongenie.handycoin.domain

import com.seongenie.handycoin.domain.infra.BaseEntity
import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.UniqueConstraint


@Table( uniqueConstraints = [UniqueConstraint(columnNames = ["exchange", "coin", "currency"])])
@Entity
open class BaseCoin() : BaseEntity() {
    lateinit var exchange : String
    lateinit var coin : String
    lateinit var coinNameKor : String
    lateinit var coinNameEng : String
    lateinit var currency : String

    constructor(exchange : String, coin : String, currency : String, coinNameKor:String= "", coinNameEng: String = "") : this() {
        this.exchange = exchange
        this.coin = coin
        this.currency = currency
        this.coinNameKor = coinNameKor
        this.coinNameEng = coinNameEng
    }
}


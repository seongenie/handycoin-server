package com.seongenie.handycoin.domain

import com.seongenie.handycoin.domain.infra.BasicEntity
import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.UniqueConstraint


@Table( uniqueConstraints = [UniqueConstraint(columnNames = ["exchange", "coin"])])
@Entity
open class BasicCoin() : BasicEntity() {
    lateinit var exchange : String
    lateinit var coin : String
    constructor(exchange : String, coin : String) : this() {
        this.exchange = exchange
        this.coin = coin
    }
}


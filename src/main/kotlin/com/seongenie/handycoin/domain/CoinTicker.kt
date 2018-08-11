package com.seongenie.handycoin.domain

import com.seongenie.handycoin.domain.infra.BaseEntity
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.persistence.UniqueConstraint

@Entity
@Table(uniqueConstraints = [(UniqueConstraint(columnNames = ["coin_id"]))])
class CoinTicker() : BaseEntity() {
    @ManyToOne lateinit var coin : BaseCoin
    var lastPrice : Double = 0.0
    var prevPrice : Double = 0.0
    var highPrice : Double = 0.0
    var lowPrice : Double = 0.0
    var volume : Double = 0.0

    constructor(coin: BaseCoin, lastPrice: Double = 0.0, prevPrice: Double = 0.0, highPrice: Double = 0.0, lowPrice: Double = 0.0, volume: Double = 0.0) : this() {
        this.coin = coin
        this.lastPrice = lastPrice
        this.prevPrice = prevPrice
        this.highPrice = highPrice
        this.lowPrice = lowPrice
        this.volume = volume
    }

}
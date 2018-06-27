package com.seongenie.handycoin.domain

import com.seongenie.handycoin.domain.infra.BasicEntity
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.persistence.UniqueConstraint

@Entity
@Table(uniqueConstraints = [(UniqueConstraint(columnNames = ["coin_id", "currency"]))])
class CoinPrice(@ManyToOne var coin : BasicCoin, var lastPrice : Double, var prevPrice : Double, var highPrice : Double, var lowPrice : Double, var currency : String) : BasicEntity()
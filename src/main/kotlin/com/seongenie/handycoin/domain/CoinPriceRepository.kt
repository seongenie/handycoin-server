package com.seongenie.handycoin.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import javax.persistence.Basic

//@Repository
interface CoinPriceRepository : JpaRepository<CoinPrice, Long> {

//    @Query("select o from coin_price o where coin_id = :coinId and currency = :currency")
//    fun findCoinPriceOne(@Param("coinId") coinId : Long?, @Param("currency") currency : String) : List<CoinPrice>

//    @Query("select o from coin_price o where coin = :coin and currency = :currency")
//    fun findCoinPriceOne(@Param("coin") coin : BasicCoin, @Param("currency") currency : String) : List<CoinPrice>

//    @Modifying
//    @Query("insert coin_price (coin_id, last_price, currency) into values(:coinId, :lastPrice, :currency)")
//    fun insertCoinPriceOne(@Param("coinId") coinId : Long, @Param("lastPrice") lastPrice : Double, @Param("currency") currency : String) : CoinPrice?
}
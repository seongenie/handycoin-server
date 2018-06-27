package com.seongenie.handycoin.domain

import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import javax.persistence.Basic
import kotlin.reflect.KClass

@Repository
interface BasicCoinRepository : JpaRepository<BasicCoin, Long> {

    @Query("select o from BasicCoin o")
    override fun findAll(): List<BasicCoin>

    @Query("select o from BasicCoin o where exchange = :exchange and coin = :coin")
    fun findBasicCoin(@Param("exchange") exchange: String, @Param("coin") coin: String): BasicCoin?
}

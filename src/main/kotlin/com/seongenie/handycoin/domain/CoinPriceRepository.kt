package com.seongenie.handycoin.domain

import com.seongenie.handycoin.domain.infra.BaiscRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import javax.persistence.Basic
import javax.persistence.TypedQuery
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Expression
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

@Repository
class CoinPriceRepository : BaiscRepository() {

    @Autowired
    lateinit var basicCoinRepository : BasicCoinRepository

    fun findCoinPrices(basicCoinList: List<BasicCoin>) : List<CoinPrice> {
        var coinId : List<Long?> = basicCoinList.map{it -> basicCoinRepository.findBasicCoin(it)!!.id}
        var builder = getCriteria()
        var query : CriteriaQuery<CoinPrice> = builder.createQuery(CoinPrice::class.java)
        var root : Root<CoinPrice> = query.from(CoinPrice::class.java)
        var exp : Expression<String> = root.get("coin_id")
        var predicate : Predicate = exp.`in`(coinId)
        return getEntityManager().createQuery(query.select(root).where(predicate)).resultList
    }

    fun findCoinPrice(coinPrice: CoinPrice) : CoinPrice {
        var builder = getCriteria()
        var query : CriteriaQuery<CoinPrice> = builder.createQuery(CoinPrice::class.java)
        var root : Root<CoinPrice> = query.from(CoinPrice::class.java)
        val basicCoin = basicCoinRepository.findBasicCoin(coinPrice.coin)
        var result : CoinPrice = getEntityManager().createQuery(query.select(root).where(builder.equal(root.get<BasicCoin>("coin").get<Long>("id"), basicCoin?.id))).singleResult
        return result
    }

    fun findAll() : List<CoinPrice> {
        var builder = getCriteria()
        var query : CriteriaQuery<CoinPrice> = builder.createQuery(CoinPrice::class.java)
        var root : Root<CoinPrice> = query.from(CoinPrice::class.java)
        query.select(root)
        var q : TypedQuery<CoinPrice> = getEntityManager().createQuery(query)
        return q.resultList
    }

    fun insertCoinPriceOne(coinPrice: CoinPrice) {
        addOne(coinPrice)
    }



//    @Query("select o from coin_price o where coin_id = :coinId and currency = :currency")
//    fun findCoinPriceOne(@Param("coinId") coinId : Long?, @Param("currency") currency : String) : List<CoinPrice>

//    @Query("select o from coin_price o where coin = :coin and currency = :currency")
//    fun findCoinPriceOne(@Param("coin") coin : BasicCoin, @Param("currency") currency : String) : List<CoinPrice>

//    @Modifying
//    @Query("insert coin_price (coin_id, last_price, currency) into values(:coinId, :lastPrice, :currency)")
//    fun insertCoinPriceOne(@Param("coinId") coinId : Long, @Param("lastPrice") lastPrice : Double, @Param("currency") currency : String) : CoinPrice?
}
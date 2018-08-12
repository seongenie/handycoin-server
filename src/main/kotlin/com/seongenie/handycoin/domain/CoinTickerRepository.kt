package com.seongenie.handycoin.domain

import com.seongenie.handycoin.domain.infra.BaseRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import javax.persistence.TypedQuery
import javax.persistence.criteria.*

@Repository
class CoinTickerRepository : BaseRepository() {

    @Autowired
    lateinit var baseCoinRepository : BaseCoinRepository

    fun findCoinTickers(baseCoinList: List<BaseCoin>) : List<CoinTicker> {
        var coinId : List<Long?> = baseCoinList.map{it -> baseCoinRepository.findBaseCoin(it)!!.id}
        var query : CriteriaQuery<CoinTicker> = builder.createQuery(CoinTicker::class.java)
        var root : Root<CoinTicker> = query.from(CoinTicker::class.java)
        var exp : Expression<String> = root.get("coin_id")
        var predicate : Predicate = exp.`in`(coinId)
        return entityManager.createQuery(query.select(root).where(predicate)).resultList
    }

    fun findCoinTicker(coinTicker: CoinTicker) : CoinTicker? {
        var query : CriteriaQuery<CoinTicker> = builder.createQuery(CoinTicker::class.java)
        var root : Root<CoinTicker> = query.from(CoinTicker::class.java)
        val baseCoin = baseCoinRepository.findBaseCoin(coinTicker.coin)
        var resultList = entityManager.createQuery(query.select(root).where(
            builder.equal(root.get<BaseCoin>("coin").get<Long>("id"), baseCoin?.id))
        ).resultList
        return if(resultList.isNotEmpty())resultList.get(0) else null
    }

    fun findAll() : List<CoinTicker> {
        var query : CriteriaQuery<CoinTicker> = builder.createQuery(CoinTicker::class.java)
        var root : Root<CoinTicker> = query.from(CoinTicker::class.java)
        query.select(root)
        var q : TypedQuery<CoinTicker> = entityManager.createQuery(query)
        return q.resultList
    }

    fun findByExchangeCoins(exchange: String, coins: List<String>) : List<CoinTicker> {
        val query : CriteriaQuery<CoinTicker> = builder.createQuery(CoinTicker::class.java)
        val root : Root<CoinTicker> = query.from(CoinTicker::class.java)
        val join: Join<CoinTicker, BaseCoin> = root.join("coin", JoinType.INNER)

        val q = entityManager.createQuery(query.select(root).where(
            builder.and(
                builder.equal(join.get<String>("exchange"), exchange),
                join.get<String>("coin").`in`(coins)
            ))
        )
        return q.resultList
    }
}
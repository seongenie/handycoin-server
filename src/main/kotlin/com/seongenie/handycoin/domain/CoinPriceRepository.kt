package com.seongenie.handycoin.domain

import com.seongenie.handycoin.domain.infra.BaseRepository
import com.seongenie.handycoin.domain.infra.HibernateUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import javax.persistence.TypedQuery
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Expression
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

@Repository
class CoinPriceRepository : BaseRepository() {

    @Autowired
    lateinit var baseCoinRepository : BaseCoinRepository

    fun findCoinPrices(baseCoinList: List<BaseCoin>) : List<CoinTicker> {
        var coinId : List<Long?> = baseCoinList.map{it -> baseCoinRepository.findBaseCoin(it)!!.id}
        var builder = getCriteria()
        var query : CriteriaQuery<CoinTicker> = builder.createQuery(CoinTicker::class.java)
        var root : Root<CoinTicker> = query.from(CoinTicker::class.java)
        var exp : Expression<String> = root.get("coin_id")
        var predicate : Predicate = exp.`in`(coinId)
        return entityManager.createQuery(query.select(root).where(predicate)).resultList
    }

    fun findCoinPrice(coinTicker: CoinTicker) : CoinTicker? {
        var builder = getCriteria()
        var query : CriteriaQuery<CoinTicker> = builder.createQuery(CoinTicker::class.java)
        var root : Root<CoinTicker> = query.from(CoinTicker::class.java)
        val baseCoin = baseCoinRepository.findBaseCoin(coinTicker.coin)
        var resultList = entityManager.createQuery(query.select(root).where(
            builder.equal(root.get<BaseCoin>("coin").get<Long>("id"), baseCoin?.id))
        ).resultList
        return if(resultList.isNotEmpty())resultList.get(0) else null
    }

    fun findAll() : List<CoinTicker> {
        var builder = getCriteria()
        var query : CriteriaQuery<CoinTicker> = builder.createQuery(CoinTicker::class.java)
        var root : Root<CoinTicker> = query.from(CoinTicker::class.java)
        query.select(root)
        var q : TypedQuery<CoinTicker> = entityManager.createQuery(query)
        return q.resultList
    }

    fun insertCoinPriceOne(coinTicker: CoinTicker) {
        add(coinTicker)
    }

    fun updateCoinPriceOne(coinTicker: CoinTicker) {
        update(coinTicker)
    }
}
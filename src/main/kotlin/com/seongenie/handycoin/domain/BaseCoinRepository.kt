package com.seongenie.handycoin.domain

import org.springframework.stereotype.Repository
import javax.persistence.TypedQuery
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root
import com.seongenie.handycoin.domain.infra.BaseRepository


@Repository
class BaseCoinRepository : BaseRepository() {

    fun findBaseCoin(baseCoin : BaseCoin) : BaseCoin? {
        var query : CriteriaQuery<BaseCoin> = builder.createQuery(BaseCoin::class.java)
        var root : Root<BaseCoin> = query.from(BaseCoin::class.java)
        var predicate : Predicate = builder.and(builder.equal(root.get<String>("exchange"), baseCoin.exchange), builder.equal(root.get<String>("coin"), baseCoin.coin))
        query = query.select(root).where(predicate)
        var q : TypedQuery<BaseCoin> = entityManager.createQuery(query)
        return q.singleResult
    }

    fun addBaseCoin(coin: BaseCoin) {
        return add(coin)
    }

    fun findAll() : List<BaseCoin> {
        var query : CriteriaQuery<BaseCoin> = builder.createQuery(BaseCoin::class.java)
        var root : Root<BaseCoin> = query.from(BaseCoin::class.java)
        query.select(root)
        var q : TypedQuery<BaseCoin> = entityManager.createQuery(query)
        return q.resultList
    }

    fun findCoins(exchange: String) : List<BaseCoin> {
        var query : CriteriaQuery<BaseCoin> = builder.createQuery(BaseCoin::class.java)
        var root : Root<BaseCoin> = query.from(BaseCoin::class.java)
        var predicate : Predicate = builder.equal(root.get<String>("exchange"), exchange)
        query.select(root).where(predicate)
        var q : TypedQuery<BaseCoin> = entityManager.createQuery(query)
        return q.resultList
    }

    fun findExchanges(coin : String) : List<BaseCoin> {
        var query : CriteriaQuery<BaseCoin> = builder.createQuery(BaseCoin::class.java)
        var root : Root<BaseCoin> = query.from(BaseCoin::class.java)
        var predicate : Predicate = builder.equal(root.get<String>("coin"), coin)
        query.select(root).where(predicate)
        var q : TypedQuery<BaseCoin> = entityManager.createQuery(query)
        return q.resultList
    }




}
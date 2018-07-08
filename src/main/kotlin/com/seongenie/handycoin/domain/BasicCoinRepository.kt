package com.seongenie.handycoin.domain

import com.seongenie.handycoin.domain.infra.BaiscRepository
import org.springframework.stereotype.Repository
import javax.persistence.TypedQuery
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Root


@Repository
class BasicCoinRepository : BaiscRepository() {

    fun findBasicCoin(exchange : String, coin : String) : BasicCoin? {
        var builder = getCriteria()
        var query : CriteriaQuery<BasicCoin> = builder.createQuery(BasicCoin::class.java)
        var root : Root<BasicCoin> = query.from(BasicCoin::class.java)
        query.select(root)
        var q : TypedQuery<BasicCoin> = getEntityManager().createQuery(query)
        return q.resultList.get(0)
    }

    fun addBasicCoin(coin: BasicCoin) {
        return getEntityManager().persist(coin)
    }

    fun findAll() : List<BasicCoin> {
        var builder = getCriteria()
        var query : CriteriaQuery<BasicCoin> = builder.createQuery(BasicCoin::class.java)
        var root : Root<BasicCoin> = query.from(BasicCoin::class.java)
        query.select(root)
        var q : TypedQuery<BasicCoin> = getEntityManager().createQuery(query)
        return q.resultList
    }




}
package com.seongenie.handycoin.domain

import com.seongenie.handycoin.domain.infra.BaiscRepository
import org.springframework.stereotype.Repository
import javax.persistence.TypedQuery
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root


@Repository
class BasicCoinRepository : BaiscRepository() {

    fun findBasicCoin(basicCoin : BasicCoin) : BasicCoin? {
        var builder = getCriteria()
        var query : CriteriaQuery<BasicCoin> = builder.createQuery(BasicCoin::class.java)
        var root : Root<BasicCoin> = query.from(BasicCoin::class.java)
        var predicate : Predicate = builder.and(builder.equal(root.get<String>("exchange"), basicCoin.exchange), builder.equal(root.get<String>("coin"), basicCoin.coin))
        query = query.select(root).where(predicate)
        var q : TypedQuery<BasicCoin> = getEntityManager().createQuery(query)
        return q.singleResult
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
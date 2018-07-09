package com.seongenie.handycoin.domain.infra

import com.seongenie.handycoin.domain.BasicCoin
import org.hibernate.Criteria
import org.hibernate.Session
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.TypedQuery
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Root

open class BaiscRepository {

    @Autowired
    lateinit var hibernateUtil: HibernateUtil

    fun getCriteria(): CriteriaBuilder {
        return hibernateUtil.getEntityManager().criteriaBuilder
    }

    fun getEntityManager(): EntityManager {
        return hibernateUtil.getEntityManager()
    }

    fun addOne(entity : Any?) {
        hibernateUtil.getEntityManager().persist(entity)
    }
}
package com.seongenie.handycoin.domain.infra

import org.hibernate.Criteria
import org.hibernate.Session
import org.springframework.beans.factory.annotation.Autowired
import javax.persistence.EntityManager
import javax.persistence.criteria.CriteriaBuilder

open class BaiscRepository {

    @Autowired
    lateinit var hibernateUtil : HibernateUtil

    fun getCriteria() : CriteriaBuilder {
        return hibernateUtil.getEntityManager().criteriaBuilder
    }

    fun getEntityManager() : EntityManager {
        return hibernateUtil.getEntityManager()
    }
}
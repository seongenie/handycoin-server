package com.seongenie.handycoin.domain.infra

import org.springframework.beans.factory.annotation.Autowired
import javax.persistence.EntityManager
import javax.persistence.EntityTransaction
import javax.persistence.PersistenceContext
import javax.persistence.criteria.CriteriaBuilder

open class BaseRepository {

    @Autowired
    lateinit var hibernateUtil: HibernateUtil

    @PersistenceContext
    protected open lateinit var entityManager: EntityManager


    fun getCriteria(): CriteriaBuilder {
//        return hibernateUtil.getEntityManager().criteriaBuilder
        return entityManager.criteriaBuilder
    }

//    fun getEntityManager(): EntityManager {
//        return hibernateUtil.getEntityManager()
//    }

    fun add(entity : Any?) {
//        var em : EntityManager = hibernateUtil.getEntityManager()
//        var transaction : EntityTransaction = em.transaction
//        transaction.begin()
        entityManager.persist(entity)
//        transaction.commit()
//        em.close()
    }

    fun update(entity : Any?) {
//        var em : EntityManager = hibernateUtil.getEntityManager()
//        var transaction : EntityTransaction = em.transaction
//        transaction.begin()
        entityManager.merge(entity)
//        transaction.commit()
//        em.close()
    }
}
package com.seongenie.handycoin.domain.infra
import org.hibernate.*
import org.hibernate.cfg.Configuration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.hibernate.SessionFactory
import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory


@Component
class HibernateUtil {

    @Autowired
    private var entityManagerFactory: EntityManagerFactory? = null
    private var entityManager : EntityManager? = null

    fun getEntityManager() : EntityManager {
        return if(entityManager != null) entityManager!! else entityManagerFactory!!.createEntityManager()
    }
}

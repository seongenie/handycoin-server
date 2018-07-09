package com.seongenie.handycoin.domain.infra
import org.hibernate.*
import org.hibernate.cfg.Configuration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.hibernate.SessionFactory
import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory
import javax.persistence.Persistence


@Component
class HibernateUtil {
    @Autowired
    lateinit var entityManagerFactory: EntityManagerFactory

    fun getEntityManager() : EntityManager {
        return entityManagerFactory.createEntityManager()
    }
}

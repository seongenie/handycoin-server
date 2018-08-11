package com.seongenie.handycoin.domain.infra
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory


@Component
class HibernateUtil {
    @Autowired
    lateinit var entityManagerFactory: EntityManagerFactory

    fun getEntityManager() : EntityManager {
        return entityManagerFactory.createEntityManager()
    }
}

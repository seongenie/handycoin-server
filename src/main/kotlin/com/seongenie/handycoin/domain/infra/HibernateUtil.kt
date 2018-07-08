package com.seongenie.handycoin.domain.infra
import org.hibernate.*
import org.hibernate.cfg.Configuration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.hibernate.SessionFactory
import javax.persistence.EntityManager


@Component
class HibernateUtil {

    @Autowired
    private var entityManager: EntityManager? = null
    var status = "-"

    fun getEntityManager() : EntityManager {
        return entityManager!!
    }
//    val session: Session @Throws(HibernateException::class) get() = entityManager.!!.openSession()
//    val currentSession: Session @Throws(HibernateException::class) get() = sessionFactory!!.currentSession

}

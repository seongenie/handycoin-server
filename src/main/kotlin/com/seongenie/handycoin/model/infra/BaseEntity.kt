package com.seongenie.handycoin.model.infra

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.util.*
import javax.persistence.*

@MappedSuperclass
abstract class BaseEntity {

    @Id
    @GeneratedValue
    var id : Long? = null

    @field:CreationTimestamp
    lateinit var createDate: Date

    @field:UpdateTimestamp
    lateinit var updateDate : Date
}
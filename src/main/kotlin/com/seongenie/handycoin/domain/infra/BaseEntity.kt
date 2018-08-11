package com.seongenie.handycoin.domain.infra

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.sql.Timestamp
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull

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
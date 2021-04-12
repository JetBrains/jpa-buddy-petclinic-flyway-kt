package com.haulmont.petclinic.model

import org.springframework.jdbc.core.JdbcTemplate
import java.io.Serializable
import javax.persistence.*
import kotlin.reflect.KClass

@MappedSuperclass
open class BaseEntity : Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    open fun isNew(): Boolean {
        return id == null
    }
}

fun KClass<out BaseEntity>.tableName(): String? {
    return this.annotations.filterIsInstance<Table>().firstOrNull()?.name
}
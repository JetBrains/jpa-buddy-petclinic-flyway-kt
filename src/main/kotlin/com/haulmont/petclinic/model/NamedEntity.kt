package com.haulmont.petclinic.model

import javax.persistence.Column
import javax.persistence.MappedSuperclass

@MappedSuperclass
open class NamedEntity : BaseEntity() {
    @Column(name = "name")
    var name: String? = null

    @Override
    override fun toString(): String {
        return "${javaClass.simpleName}(name = $name)"
    }
}
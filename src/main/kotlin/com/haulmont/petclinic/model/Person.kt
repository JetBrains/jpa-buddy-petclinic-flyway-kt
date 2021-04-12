package com.haulmont.petclinic.model

import javax.persistence.Column
import javax.persistence.MappedSuperclass
import javax.validation.constraints.NotEmpty

@MappedSuperclass
open class Person : BaseEntity() {
    @NotEmpty
    @Column(name = "first_name")
    var firstName: String? = null

    @NotEmpty
    @Column(name = "last_name")
    var lastName: String? = null
}
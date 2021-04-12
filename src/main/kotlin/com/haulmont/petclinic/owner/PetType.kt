package com.haulmont.petclinic.owner

import com.haulmont.petclinic.model.NamedEntity
import javax.persistence.Entity
import javax.persistence.Table

@Table(name = "types")
@Entity
class PetType : NamedEntity()
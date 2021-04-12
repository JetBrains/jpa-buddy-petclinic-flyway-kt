package com.haulmont.petclinic.vet

import com.haulmont.petclinic.model.NamedEntity
import javax.persistence.Entity
import javax.persistence.Table

@Table(name = "specialties")
@Entity
class Specialty : NamedEntity()
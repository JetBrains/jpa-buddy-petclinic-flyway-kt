package com.haulmont.petclinic.owner

import com.haulmont.petclinic.model.NamedEntity
import com.haulmont.petclinic.visit.Visit
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import java.util.*
import javax.persistence.*
import kotlin.jvm.Transient

@Table(name = "pets")
@Entity
class Pet : NamedEntity() {

    @Column(name = "birth_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    var birthDate: LocalDate? = null

    @JoinColumn(name = "type_id")
    @ManyToOne
    var type: PetType? = null

    @JoinColumn(name = "owner_id")
    @ManyToOne
    var owner: Owner? = null

    @Transient
    var visits = mutableSetOf<Visit>()

    fun addVisit(visit: Visit) {
        visits.add(visit)
        visit.petId = id
    }
}
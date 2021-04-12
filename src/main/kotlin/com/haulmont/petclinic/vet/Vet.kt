package com.haulmont.petclinic.vet

import com.haulmont.petclinic.model.Person
import javax.persistence.*

@Table(name = "vets")
@Entity
class Vet : Person() {
    @JoinTable(
        name = "vet_specialties",
        joinColumns = [JoinColumn(name = "vet_id")],
        inverseJoinColumns = [JoinColumn(name = "specialty_id")]
    )
    @ManyToMany(fetch = FetchType.EAGER)
    var specialties: MutableSet<Specialty> = mutableSetOf()

    fun getNrOfSpecialties(): Int {
        return specialties.size
    }

    fun addSpecialty(specialty: Specialty?) {
        specialty?.let { specialties.add(it) }
    }
}
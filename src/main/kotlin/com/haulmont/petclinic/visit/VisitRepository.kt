package com.haulmont.petclinic.visit;

import org.springframework.data.jpa.repository.JpaRepository

interface VisitRepository : JpaRepository<Visit, Int> {
    fun findByPetId(petId: Int): List<Visit>

}
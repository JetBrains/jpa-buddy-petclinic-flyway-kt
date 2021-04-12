package com.haulmont.petclinic.owner;

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Transactional

interface PetRepository : JpaRepository<Pet, Int> {
    @Query("select p from PetType p order by p.name")
    @Transactional(readOnly = true)
    fun findPetTypes(): List<PetType>
}
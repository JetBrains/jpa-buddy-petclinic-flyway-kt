package com.haulmont.petclinic.owner;

import org.springframework.data.jpa.repository.JpaRepository

interface PetTypeRepository : JpaRepository<PetType, Int> {
    fun findOneByName(name: String): PetType?
}
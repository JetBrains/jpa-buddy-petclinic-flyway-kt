package com.haulmont.petclinic.owner;

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.Repository
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional

interface OwnerRepository : Repository<Owner, Int> {
    @Query("select distinct o from Owner o left join fetch o.pets where o.lastName like :lastName%")
    @Transactional(readOnly = true)
    fun findByLastName(@Param("lastName") lastName: String): Collection<Owner>

    @Query("select o from Owner o left join fetch o.pets where o.id = :id")
    fun findById(@Param("id") id: Int): Owner?

    fun save(owner: Owner)
}
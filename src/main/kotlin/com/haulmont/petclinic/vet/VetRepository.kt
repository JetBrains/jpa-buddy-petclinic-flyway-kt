package com.haulmont.petclinic.vet;

import org.springframework.cache.annotation.Cacheable
import org.springframework.dao.DataAccessException
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.Repository
import org.springframework.transaction.annotation.Transactional

interface VetRepository : JpaRepository<Vet, Int> {
    @Transactional(readOnly = true)
    @Cacheable("vets")
    @Throws(DataAccessException::class)
    override fun findAll(): List<Vet>
}
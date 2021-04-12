package com.haulmont.petclinic.owner

import com.haulmont.petclinic.model.BaseEntity
import com.haulmont.petclinic.model.tableName
import com.haulmont.petclinic.vet.Vet
import com.haulmont.petclinic.vet.VetRepository
import com.haulmont.petclinic.visit.Visit
import com.haulmont.petclinic.visit.VisitRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.context.ActiveProfiles
import java.time.LocalDate
import kotlin.reflect.KClass

@SpringBootTest
@ActiveProfiles("test")
class PetClinicTest {

    @Autowired
    private lateinit var ownerRepo: OwnerRepository
    @Autowired
    private lateinit var petRepo: PetRepository
    @Autowired
    private lateinit var vetRepo: VetRepository
    @Autowired
    private lateinit var petTypeRepo: PetTypeRepository
    @Autowired
    private lateinit var visitRepo: VisitRepository
    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate

    @BeforeEach
    internal fun setUp() {
        val dog = PetType().apply {
            name = "dog"
        }
        petTypeRepo.save(dog)

        val george = Owner().apply {
            firstName = "George"
            lastName = "Franklin"
            address = "110 W. Liberty St."
            city = "Madison"
            telephone = "6085551023"
        }
        ownerRepo.save(george)

        val max = Pet().apply {
            type = dog
            name = "Max"
            birthDate = LocalDate.now()
            owner = george
        }
        petRepo.save(max)

        vetRepo.save(Vet().apply {
            firstName = "Zaphod"
            lastName = "Beeblebrox"
        })

        visitRepo.save(Visit().apply {
            description = "First visit"
            petId = max.id
        })
    }

    @AfterEach
    internal fun tearDown() {
        jdbcTemplate.also {
            it.deleteFrom(Pet::class)
            it.deleteFrom(Owner::class)
            it.deleteFrom(PetType::class)
            it.deleteFrom(Vet::class)
            it.deleteFrom(Visit::class)
        }
    }

    @Test
    internal fun testOwnerRepo() {
        val owners = ownerRepo.findByLastName("Frank")
        assertEquals(1, owners.size)

        val george = owners.first()
        assertEquals("George", george.firstName)

        val pets = george.pets
        assertEquals(1, pets.size)

        val pet = pets.first()
        assertEquals("Max", pet.name)
        assertEquals("dog", pet.type?.name)

        val georgeFoundById = ownerRepo.findById(george.id!!)
        assertNotNull(georgeFoundById)
    }

    @Test
    internal fun testVetRepo() {
        val allVets = vetRepo.findAll()
        assertEquals(1, allVets.size)

        val vet = allVets.first()
        assertEquals("Zaphod", vet.firstName)
    }

    @Test
    internal fun testPetRepo() {
        val pets = petRepo.findAll()
        assertEquals(1, pets.size)

        val pet = pets.first()
        assertEquals("Max", pet.name)

        val petTypes = petRepo.findPetTypes()
        assertEquals(1, petTypes.size)
        assertEquals("dog", petTypes.first().name)
    }

    @Test
    internal fun testVisitRepo() {
        val petType = petTypeRepo.findOneByName("dog")
        assertNotNull(petType)

        val visits = visitRepo.findByPetId(petType?.id!!)
        assertEquals(1, visits.size)

        val visit = visits.first()
        assertEquals("First visit", visit.description)
    }
}

private fun JdbcTemplate.deleteFrom(entityClass: KClass<out BaseEntity>) {
    this.execute("delete from ${entityClass.tableName()}")
}
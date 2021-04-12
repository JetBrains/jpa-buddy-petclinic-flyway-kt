package com.haulmont.petclinic.visit

import com.haulmont.petclinic.model.BaseEntity
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Convert
import javax.persistence.Entity
import javax.persistence.Table
import javax.validation.constraints.NotEmpty

@Table(name = "visits")
@Entity
class Visit : BaseEntity() {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "visit_date", nullable = false)
    var date: LocalDate? = LocalDate.now()

    @NotEmpty
    @Column(name = "description", length = 1000)
    var description: String? = null

    @Column(name = "pet_id")
    var petId: Int? = null
}
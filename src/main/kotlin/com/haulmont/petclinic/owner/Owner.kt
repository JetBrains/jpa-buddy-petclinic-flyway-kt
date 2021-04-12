package com.haulmont.petclinic.owner

import com.haulmont.petclinic.model.Person
import org.springframework.beans.support.MutableSortDefinition
import org.springframework.beans.support.PropertyComparator
import org.springframework.core.style.ToStringCreator
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Digits
import javax.validation.constraints.NotEmpty

@Table(name = "owners")
@Entity
class Owner : Person() {
    @NotEmpty
    @Column(name = "address")
    var address: String? = null

    @NotEmpty
    @Column(name = "city")
    var city: String? = null

    @NotEmpty
    @Digits(fraction = 0, integer = 10)
    @Column(name = "telephone")
    var telephone: String? = null

    @OneToMany(mappedBy = "owner", cascade = [CascadeType.ALL])
    var pets: MutableSet<Pet> = mutableSetOf()

    /**
     * Return the Pet with the given name, or null if none found for this Owner.
     * @param name to test
     * @return true if pet name is already in use
     */
    fun getPet(name: String?): Pet? {
        return getPet(name, false)
    }

    /**
     * Return the Pet with the given name, or null if none found for this Owner.
     * @param name to test
     * @return true if pet name is already in use
     */
    fun getPet(name: String?, ignoreNew: Boolean): Pet? {
        if (name == null) {
            return null
        }
        return pets.firstOrNull {
            (!ignoreNew || !it.isNew()) && name.equals(it.name, true)
        }
    }

    override fun toString(): String {
        return ToStringCreator(this)
            .append("id", id)
            .append("new", isNew())
            .append("lastName", lastName)
            .append("firstName", firstName)
            .append("address", address)
            .append("city", city)
            .append("telephone", telephone).toString()
    }
}
package cabanas.garcia.ismael.meetup.payment.infrastructure.repository.jpa.entities

import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "PAYERS")
class PayerEntity() {
    constructor(id: UUID, login: String, email: String, firstName: String, lastName: String) : this() {
        this.id = id
        this.login = login
        this.email = email
        this.firstName = firstName
        this.lastName = lastName
    }

    @Id
    lateinit var id: UUID
    lateinit var login: String
    lateinit var email: String
    lateinit var firstName: String
    lateinit var lastName: String
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PayerEntity

        if (id != other.id) return false
        if (login != other.login) return false
        if (email != other.email) return false
        if (firstName != other.firstName) return false
        if (lastName != other.lastName) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + login.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + firstName.hashCode()
        result = 31 * result + lastName.hashCode()
        return result
    }


}

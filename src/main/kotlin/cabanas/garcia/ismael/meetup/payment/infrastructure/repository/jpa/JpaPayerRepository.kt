package cabanas.garcia.ismael.meetup.payment.infrastructure.repository.jpa

import cabanas.garcia.ismael.meetup.payment.domain.Payer
import cabanas.garcia.ismael.meetup.payment.domain.PayerId
import cabanas.garcia.ismael.meetup.payment.domain.PayerRepository
import cabanas.garcia.ismael.meetup.payment.infrastructure.repository.jpa.entities.PayerEntity
import cabanas.garcia.ismael.meetup.payment.infrastructure.repository.jpa.spring.SpringJpaPayerRepository
import java.util.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository

class JpaPayerRepository(
    private val jpaRepository: SpringJpaPayerRepository
) : PayerRepository {
    override fun get(id: PayerId): Payer? =
        jpaRepository.findById(UUID.fromString(id.value))
            .map { entity ->
                Payer(
                    PayerId(entity.id.toString()),
                    entity.email,
                    entity.firstName,
                    entity.lastName
                )
            }.unwrap()

    override fun add(payer: Payer) {
        jpaRepository.save(
            PayerEntity(
                UUID.fromString(payer.id.value),
                payer.email,
                payer.email,
                payer.firstName,
                payer.lastName
            )
        )
    }
}

fun <T> Optional<T>.unwrap(): T? = orElse(null)
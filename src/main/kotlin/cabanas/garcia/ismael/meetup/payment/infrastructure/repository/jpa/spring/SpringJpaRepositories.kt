package cabanas.garcia.ismael.meetup.payment.infrastructure.repository.jpa.spring

import cabanas.garcia.ismael.meetup.payment.infrastructure.repository.jpa.entities.PayerEntity
import cabanas.garcia.ismael.meetup.payment.infrastructure.repository.jpa.entities.JpaSubscriptionPayment
import java.util.*
import org.springframework.data.repository.CrudRepository

interface SpringJpaPayerRepository : CrudRepository<PayerEntity, UUID>

interface SpringJpaSubscriptionRepository : CrudRepository<JpaSubscriptionPayment, UUID>

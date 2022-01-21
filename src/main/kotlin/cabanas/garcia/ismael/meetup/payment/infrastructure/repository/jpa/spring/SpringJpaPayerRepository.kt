package cabanas.garcia.ismael.meetup.payment.infrastructure.repository.jpa.spring

import cabanas.garcia.ismael.meetup.payment.infrastructure.repository.jpa.entities.PayerEntity
import java.util.UUID
import org.springframework.data.repository.CrudRepository

interface SpringJpaPayerRepository : CrudRepository<PayerEntity, UUID>
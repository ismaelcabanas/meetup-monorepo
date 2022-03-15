package cabanas.garcia.ismael.meetup.payment.infrastructure.repository.jpa.entities

import java.math.BigDecimal
import java.time.Instant
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "SUBSCRIPTION_PAYMENTS")
data class JpaSubscriptionPayment (
    @Id
    val id: UUID,
    @Column(nullable = false)
    val payerId: UUID,
    @Column(length = 50, nullable = false)
    val type: String,
    @Column(length = 50, nullable = false)
    val status: String,
    @Column(length = 50, nullable = false)
    val period: String,
    @Column(nullable = false)
    val startDate: Instant,
    @Column(nullable = false, precision = 18, scale = 2)
    val moneyValue: BigDecimal
)

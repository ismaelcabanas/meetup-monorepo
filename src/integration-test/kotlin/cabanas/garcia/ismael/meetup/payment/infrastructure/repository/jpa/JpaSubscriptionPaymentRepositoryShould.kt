package cabanas.garcia.ismael.meetup.payment.infrastructure.repository.jpa

import cabanas.garcia.ismael.meetup.payment.domain.subscriptionpayments.SubscriptionPaymentRepository
import cabanas.garcia.ismael.meetup.payment.domain.subscriptionpayments.SubscriptionPeriod
import cabanas.garcia.ismael.meetup.payment.domain.subscriptionpayments.SubscriptionType
import cabanas.garcia.ismael.meetup.payment.infrastructure.configuration.PaymentRepositoryConfiguration
import cabanas.garcia.ismael.meetup.payments.domain.subscriptionpayments.SubscriptionPaymentMother
import java.time.Instant
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.SqlGroup

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Import(PaymentRepositoryConfiguration::class)
class JpaSubscriptionPaymentRepositoryShould {
    @Autowired
    private lateinit var repository: SubscriptionPaymentRepository

    @Test
    @SqlGroup(
        Sql(
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            statements = [
                "INSERT INTO subscription_payments (ID, PAYER_ID, TYPE, STATUS, PERIOD, START_DATE, MONEY_VALUE) " +
                "VALUES ('$SOME_UUID', '$SOME_PAYER_ID', '$STANDARD_TYPE', '$WAITING_FOR_PAYMENT', '$ANNUAL_PERIOD', '$SOME_STARTING_SUBSCRIPTION_DATE', $SOME_MONEY_VALUE)"
            ]
        ),
        Sql(
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            statements = [
                "DELETE FROM subscription_payments"
            ]
        )
    )
    fun `get subscription payment by given identifier`() {
        val expectedSubscriptionPayment = SubscriptionPaymentMother.waitingForPayment(
            id = SOME_UUID,
            payerId = SOME_PAYER_ID,
            type = SubscriptionType.STANDARD,
            period = SubscriptionPeriod.ANNUAL,
            price = SOME_MONEY_VALUE,
            startDate = Instant.parse(SOME_STARTING_SUBSCRIPTION_DATE)
        )

        val subscriptionPayment = repository.get(expectedSubscriptionPayment.paymentId)

        assertThat(subscriptionPayment).isNotNull
        assertThat(subscriptionPayment).isEqualTo(expectedSubscriptionPayment)
    }

    private companion object {
        private const val SOME_UUID = "e14e10a9-838f-4ea1-bdc1-4651718158f7"
        private const val SOME_PAYER_ID = "4c68fd2f-ecc7-4ce2-8a95-78bdf745dd39"
        private const val STANDARD_TYPE = "STANDARD"
        private const val WAITING_FOR_PAYMENT = "WAITING_FOR_PAYMENT"
        private const val ANNUAL_PERIOD = "ANNUAL"
        private const val SOME_MONEY_VALUE = 100.0
        private const val SOME_STARTING_SUBSCRIPTION_DATE = "2021-05-14T17:26:56.000000Z"
    }

}
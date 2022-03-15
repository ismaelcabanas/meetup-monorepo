package cabanas.garcia.ismael.meetup.payment.infrastructure.repository.jpa

import cabanas.garcia.ismael.meetup.payment.domain.Payer
import cabanas.garcia.ismael.meetup.payment.domain.PayerId
import cabanas.garcia.ismael.meetup.payment.domain.PayerRepository
import cabanas.garcia.ismael.meetup.payment.infrastructure.configuration.PaymentRepositoryConfiguration
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD
import org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD
import org.springframework.test.context.jdbc.SqlGroup

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
@Import(PaymentRepositoryConfiguration::class)
class JpaPayerRepositoryShould {
    @Autowired
    private lateinit var payerRepository: PayerRepository

    @Test
    @SqlGroup(
        Sql(
            executionPhase = BEFORE_TEST_METHOD,
            statements = [
                "INSERT INTO payers (ID, LOGIN, EMAIL, FIRST_NAME, LAST_NAME) " +
                    "VALUES ('$SOME_UUID', '$SOME_LOGIN', '$SOME_EMAIL', '$SOME_FIRST_NAME', '$SOME_LAST_NAME')"
            ]
        ),
        Sql(
            executionPhase = AFTER_TEST_METHOD,
            statements = [
                "DELETE FROM payers"
            ]
        )
    )
    fun `get payer by given identifier`() {
        val expectedPayer = Payer.create(
            PayerId(SOME_UUID),
            SOME_EMAIL,
            SOME_FIRST_NAME,
            SOME_LAST_NAME
        )

        val payer = payerRepository.get(PayerId(SOME_UUID))

        assertThat(payer).isNotNull
        assertThat(payer).isEqualTo(expectedPayer)
    }

    private companion object {
        private const val SOME_UUID = "e14e10a9-838f-4ea1-bdc1-4651718158f7"
        private const val SOME_LOGIN = "ismael.cabanas@test.com"
        private const val SOME_EMAIL = "ismael.cabanas@test.com"
        private const val SOME_FIRST_NAME = "Ismael"
        private const val SOME_LAST_NAME = "Cabañas García"
    }
}

package cabanas.garcia.ismael.meetup.payments.infrastructure.repository.jpa

import cabanas.garcia.ismael.meetup.payment.domain.PayerId
import cabanas.garcia.ismael.meetup.payment.infrastructure.repository.jpa.JpaPayerRepository
import cabanas.garcia.ismael.meetup.payment.infrastructure.repository.jpa.entities.PayerEntity
import cabanas.garcia.ismael.meetup.payment.infrastructure.repository.jpa.spring.SpringJpaPayerRepository
import cabanas.garcia.ismael.meetup.payments.domain.PayerMother
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import java.util.UUID
import java.util.Optional
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class JpaPayerRepositoryShould {
    private val jpaRepository: SpringJpaPayerRepository = mockk()

    private val payerRepository: JpaPayerRepository = JpaPayerRepository(jpaRepository)

    @Test
    fun `GIVEN a payer exist WHEN get it by identifier from repository THEN return the payer`() {
        val expectedPayer = PayerMother.random()
        every { jpaRepository.findById(UUID.fromString(expectedPayer.id.value)) } returns
            Optional.of(
                PayerEntity(
                    UUID.fromString(expectedPayer.id.value),
                    expectedPayer.email,
                    expectedPayer.email,
                    expectedPayer.firstName,
                    expectedPayer.lastName
                )
            )

        val payer = payerRepository.get(expectedPayer.id)

        assertThat(payer).isEqualTo(expectedPayer)
    }

    @Test
    fun `GIVEN a payer does not exist WHEN get it by identifier from repository THEN return null`() {
        every { jpaRepository.findById(UUID.fromString(SOME_ID)) }.returns(Optional.empty())

        val payer = payerRepository.get(PayerId(SOME_ID))

        assertThat(payer).isNull()
    }

    @Test
    fun `WHEN adds a new payer THEN the payer is added to repository`() {
        val newPayer = PayerMother.random()
        val expectedPayerEntity = PayerEntity(
            UUID.fromString(newPayer.id.value),
            newPayer.email,
            newPayer.email,
            newPayer.firstName,
            newPayer.lastName
        )
        every { jpaRepository.save(expectedPayerEntity) } returns expectedPayerEntity

        payerRepository.add(newPayer)

        verify(exactly = 1) {
            jpaRepository.save(
                expectedPayerEntity
            )
        }
    }

    companion object {
        private const val SOME_ID = "e14e10a9-838f-4ea1-bdc1-4651718158f7"
    }
}
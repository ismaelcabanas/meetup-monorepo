package cabanas.garcia.ismael.meetup.payments.domain

import cabanas.garcia.ismael.meetup.payment.domain.Payer
import cabanas.garcia.ismael.meetup.payment.domain.PayerId
import com.github.javafaker.Faker

object PayerMother {
    private var faker = Faker()

    fun random(): Payer =
        Payer(
            PayerId(faker.internet().uuid()),
            faker.internet().emailAddress(),
            faker.name().firstName(),
            faker.name().lastName()
        )
}
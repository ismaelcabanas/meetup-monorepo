package cabanas.garcia.ismael.meetup.payments.application.createpayer

import cabanas.garcia.ismael.meetup.payment.application.createpayer.CreatePayerCommand
import com.github.javafaker.Faker

object CreatePayerCommandMother {
    private var faker = Faker()
    
    fun random(): CreatePayerCommand =
        CreatePayerCommand(
            faker.internet().uuid(),
            faker.internet().emailAddress(),
            faker.name().firstName(),
            faker.name().lastName()
        )
}
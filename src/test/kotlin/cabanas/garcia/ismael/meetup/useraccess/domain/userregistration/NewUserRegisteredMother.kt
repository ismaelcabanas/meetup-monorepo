package cabanas.garcia.ismael.meetup.useraccess.domain.userregistration

import com.github.javafaker.Faker

object NewUserRegisteredMother {
    private var faker = Faker()

    fun random(): NewUserRegistered =
        NewUserRegistered(
            faker.internet().uuid(),
            faker.internet().emailAddress(),
            faker.internet().emailAddress(),
            faker.name().firstName(),
            faker.name().lastName()
        )
}
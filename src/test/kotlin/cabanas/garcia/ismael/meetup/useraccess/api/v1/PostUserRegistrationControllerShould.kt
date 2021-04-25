package cabanas.garcia.ismael.meetup.useraccess.api.v1

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class PostUserRegistrationControllerShould {
    @Test
    fun `return 201 when post a user registration`() {
        val controller = PostUserRegistrationController()
        val requestBody = CreateUserRegistrationRequestMother.random()

        val response = controller.execute(requestBody)

        response shouldBe ResponseEntity(HttpStatus.CREATED)
    }
}
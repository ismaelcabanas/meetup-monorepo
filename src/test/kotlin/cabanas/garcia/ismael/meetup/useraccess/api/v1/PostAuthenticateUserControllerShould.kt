package cabanas.garcia.ismael.meetup.useraccess.api.v1

import cabanas.garcia.ismael.meetup.shared.application.SuccessCommandBus
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class PostAuthenticateUserControllerShould {
    @Test
    fun `return 200 when post a user`() {
        val controller = PostAuthenticateUserController(SuccessCommandBus())
        val requestBody = AuthenticateUserRequestMother.random()

        val response = controller.execute(requestBody)

        response shouldBe ResponseEntity(HttpStatus.OK)
    }
}
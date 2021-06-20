package cabanas.garcia.ismael.meetup.useraccess.api.v1

import cabanas.garcia.ismael.meetup.shared.application.SuccessCommandBus
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.util.UUID

class PatchConfirmUserRegistrationControllerShould {
    @Test
    fun `return 200 when patch to confirm a user registration`() {
        val controller = PatchConfirmUserRegistrationController(SuccessCommandBus())

        val response = controller.execute(userRegistrationId = UUID.randomUUID().toString())

        response shouldBe ResponseEntity(HttpStatus.OK)
    }
}
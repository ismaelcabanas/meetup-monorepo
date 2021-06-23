package cabanas.garcia.ismael.meetup

import cabanas.garcia.ismael.meetup.useraccess.api.v1.CreateUserRegistrationRequest
import io.restassured.http.ContentType
import io.restassured.module.mockmvc.RestAssuredMockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

abstract class BaseAcceptanceTest {
    protected fun givenUserRegisterWith(
        userId: String,
        firstName: String,
        lastName: String,
        login: String,
        password: String
    ) {
        val requestBody = CreateUserRegistrationRequest(
            userId,
            firstName,
            lastName,
            login,
            password
        )
        RestAssuredMockMvc.given()
            .contentType(ContentType.JSON)
            .body(requestBody)
            .`when`()
            .post("/v1/user-registrations")
            .then()
            .log().all()
            .assertThat(MockMvcResultMatchers.status().isCreated)
    }

    protected fun andUserConfirmRegistration(userId: String) {
        RestAssuredMockMvc.given()
            .contentType(ContentType.JSON)
            .`when`()
            .patch("/v1/user-registrations/${userId}/confirm")
            .then()
            .log().all()
            .assertThat(MockMvcResultMatchers.status().isOk)
    }

    protected fun whenUserConfirmRegistration(userId: String) {
        andUserConfirmRegistration(userId)
    }
}
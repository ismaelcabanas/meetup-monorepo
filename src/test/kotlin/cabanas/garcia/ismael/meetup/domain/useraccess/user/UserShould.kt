package cabanas.garcia.ismael.meetup.domain.useraccess.user

import io.kotest.matchers.collections.shouldContain
import org.junit.jupiter.api.Test

class UserShould {
    @Test
    fun `add role`() {
        val user = UserMother.aUserWithoutRoles()

        val userWithRole = user.addRole(Role(RoleId(SOME_ROLE_ID), SOME_ROLE_NAME))

        userWithRole.roles() shouldContain Role(RoleId(SOME_ROLE_ID), SOME_ROLE_NAME)
    }

    companion object {
        const val SOME_ROLE_ID = "some role id"
        const val SOME_ROLE_NAME = "some role name"
    }
}
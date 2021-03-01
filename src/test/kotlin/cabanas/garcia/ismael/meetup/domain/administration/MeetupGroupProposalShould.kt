package cabanas.garcia.ismael.meetup.domain.administration

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.time.Instant

class MeetupGroupProposalShould {
    @Test
    fun `create a successful meetup group proposal`() {
        val meetupGroupProposal =
            MeetupGroupProposalFactory.createProposal(SOME_ID, SOME_USER_ID, SOME_NAME, SOME_DESCRIPTION, SOME_COUNTRY, SOME_CITY, Instant.parse(SOME_DATE))

        meetupGroupProposal.events() shouldContain
            MeetupGroupProposalCreated(
                    SOME_ID,
                    SOME_USER_ID,
                    SOME_NAME,
                    SOME_DESCRIPTION,
                    SOME_COUNTRY,
                    SOME_CITY,
                    Instant.parse(SOME_DATE)
                )
        meetupGroupProposal.state().value() shouldBe MeetupGroupProposalStatus.PENDING_OF_APPROVAL
    }

    @Test
    fun `approval a pending meetup group proposal by admin user`() {
        val meetupGroupProposalPendingOfApproval = MeetupGroupProposalMother.aMeetupGroupProposalPendingOfApproval()
        val adminUser = UserMother.aAdminUser()

        val meetupGroupProposalApproved = meetupGroupProposalPendingOfApproval.approve(adminUser)

        meetupGroupProposalApproved.events() shouldContain
                MeetupGroupProposalApproved(
                    meetupGroupProposalPendingOfApproval.id.value,
                    meetupGroupProposalPendingOfApproval.proposalUserId.value,
                    meetupGroupProposalApproved.approveDate()!!
                )
        meetupGroupProposalApproved.state().value() shouldBe MeetupGroupProposalStatus.APPROVED
    }

    @Test
    fun `fail when approval a pending meetup group proposal by not admin user`() {
        val meetupGroupProposalPendingOfApproval = MeetupGroupProposalMother.aMeetupGroupProposalPendingOfApproval()
        val memberUser = UserMother.aMemberUser()

        val exception = shouldThrow<MeetupGroupProposalCannotBeApprovedException> {
            meetupGroupProposalPendingOfApproval.approve(memberUser)
        }

        exception.message shouldBe "User '${memberUser.id.value}' is not Admin and cannot approve the proposal."
    }

    @Test
    fun `fail to approve a already approved meetup group proposal`() {
        val meetupGroupProposalApproved = MeetupGroupProposalMother.aMeetupGroupProposalApproved()
        val memberUser = UserMother.aMemberUser()

        val exception = shouldThrow<MeetupGroupProposalAlreadyApprovedException> {
            meetupGroupProposalApproved.approve(memberUser)
        }

        exception.message shouldBe "Meetup group proposal '${meetupGroupProposalApproved.id.value}' already approved."
    }

    @Test
    fun `reject a pending meetup group proposal by admin user with a reason`() {
        val meetupGroupProposalPendingOfApproval = MeetupGroupProposalMother.aMeetupGroupProposalPendingOfApproval()
        val adminUser = UserMother.aAdminUser()
        val rejectedReason = "some reason"

        val meetupGroupProposalRejected =
            meetupGroupProposalPendingOfApproval.reject(adminUser, rejectedReason)

        meetupGroupProposalRejected.events() shouldContain
                MeetupGroupProposalRejected(
                    meetupGroupProposalPendingOfApproval.id.value,
                    meetupGroupProposalPendingOfApproval.proposalUserId.value,
                    rejectedReason,
                    meetupGroupProposalRejected.rejectDate()!!
                )
        meetupGroupProposalRejected.state().value() shouldBe MeetupGroupProposalStatus.REJECTED
    }

    @Test
    fun `fail when reject a pending meetup group proposal by admin user without reason`() {
        val meetupGroupProposalPendingOfApproval = MeetupGroupProposalMother.aMeetupGroupProposalPendingOfApproval()
        val adminUser = UserMother.aAdminUser()
        val emptyRejectedReason = ""

        val exception = shouldThrow<MeetupGroupProposalRequireReasonException> {
            meetupGroupProposalPendingOfApproval.reject(adminUser, emptyRejectedReason)
        }

        exception.message shouldBe "A reason is a required to reject a meetup group proposal."
    }

    @Test
    fun `fail when reject a pending meetup group proposal by not admin user`() {
        val meetupGroupProposalPendingOfApproval = MeetupGroupProposalMother.aMeetupGroupProposalPendingOfApproval()
        val memberUser = UserMother.aMemberUser()
        val rejectedReason = "some reason"

        val exception = shouldThrow<MeetupGroupProposalCannotBeRejectedException> {
            meetupGroupProposalPendingOfApproval.reject(memberUser, rejectedReason)
        }

        exception.message shouldBe "User '${memberUser.id.value}' is not Admin and cannot reject the proposal."
    }

    @Test
    fun `fail to reject a already rejected meetup group proposal`() {
        val meetupGroupProposalRejected = MeetupGroupProposalMother.aMeetupGroupProposalRejected()
        val memberUser = UserMother.aMemberUser()
        val rejectedReason = "some reason"

        val exception = shouldThrow<MeetupGroupProposalAlreadyRejectedException> {
            meetupGroupProposalRejected.reject(memberUser, rejectedReason)
        }

        exception.message shouldBe "Meetup group proposal '${meetupGroupProposalRejected.id.value}' already rejected."
    }

    @Test
    fun `fail to approved a rejected meetup group proposal`() {
        val meetupGroupProposalRejected = MeetupGroupProposalMother.aMeetupGroupProposalRejected()
        val memberUser = UserMother.aMemberUser()

        val exception = shouldThrow<IllegalStateException> {
            meetupGroupProposalRejected.approve(memberUser)
        }

        exception.message shouldBe "Cannot approve a rejected meetup group proposal."
    }

    @Test
    fun `fail to propose a rejected meetup group proposal`() {
        val meetupGroupProposalRejected = MeetupGroupProposalMother.aMeetupGroupProposalRejected()

        val exception = shouldThrow<IllegalStateException> {
            meetupGroupProposalRejected.proposal()
        }

        exception.message shouldBe "Cannot propose a rejected meetup group proposal."
    }

    @Test
    fun `fail to reject a approved meetup group proposal`() {
        val meetupGroupProposalApproved = MeetupGroupProposalMother.aMeetupGroupProposalApproved()
        val memberUser = UserMother.aMemberUser()
        val rejectedReason = "some reason"

        val exception = shouldThrow<IllegalStateException> {
            meetupGroupProposalApproved.reject(memberUser, rejectedReason)
        }

        exception.message shouldBe "Cannot reject a approved meetup group proposal."
    }

    @Test
    fun `fail to propose a approved meetup group proposal`() {
        val meetupGroupProposalApproved = MeetupGroupProposalMother.aMeetupGroupProposalApproved()

        val exception = shouldThrow<IllegalStateException> {
            meetupGroupProposalApproved.proposal()
        }

        exception.message shouldBe "Cannot pending of approval a approved meetup group proposal."
    }

    private companion object {
        private const val SOME_ID = "some id"
        private const val SOME_USER_ID = "some user id"
        private const val SOME_COUNTRY = "some country"
        private const val SOME_CITY = "some city"
        private const val SOME_NAME = "some name"
        private const val SOME_DESCRIPTION = "some description"
        private const val SOME_DATE = "2021-01-25T13:13:03Z"
    }
}
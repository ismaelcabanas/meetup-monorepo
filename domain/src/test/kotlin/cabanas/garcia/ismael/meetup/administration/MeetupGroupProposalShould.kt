package cabanas.garcia.ismael.meetup.administration

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
                    meetupGroupProposal.id,
                    meetupGroupProposal.country,
                    meetupGroupProposal.city,
                    meetupGroupProposal.date
                )
        meetupGroupProposal.status shouldBe MeetupGroupProposalStatus.PENDING_OF_APPROVAL
    }

    @Test
    fun `approval a pending meetup group proposal by admin user`() {
        val meetupGroupProposalPendingOfApproval = MeetupGroupProposalMother.aMeetupGroupProposalPendingOfApproval()
        val adminUser = UserMother.aAdminUser()

        val meetupGroupProposalApproved = meetupGroupProposalPendingOfApproval.approve(adminUser)

        meetupGroupProposalApproved.events() shouldContain
                MeetupGroupProposalApproved(
                    meetupGroupProposalApproved.id,
                    meetupGroupProposalApproved.proposalUserId
                )
        meetupGroupProposalApproved.status shouldBe MeetupGroupProposalStatus.APPROVED
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

        exception.message shouldBe "Meetup group proposal '${meetupGroupProposalApproved.id}' already approved."
    }

    @Test
    fun `reject a pending meetup group proposal by admin user`() {
        val meetupGroupProposalPendingOfApproval = MeetupGroupProposalMother.aMeetupGroupProposalPendingOfApproval()
        val adminUser = UserMother.aAdminUser()

        val meetupGroupProposalRejected = meetupGroupProposalPendingOfApproval.reject(adminUser)

        meetupGroupProposalRejected.events() shouldContain
                MeetupGroupProposalRejected(
                    meetupGroupProposalRejected.id,
                    meetupGroupProposalRejected.proposalUserId
                )
        meetupGroupProposalRejected.status shouldBe MeetupGroupProposalStatus.REJECTED
    }

    @Test
    fun `fail when reject a pending meetup group proposal by not admin user`() {
        val meetupGroupProposalPendingOfApproval = MeetupGroupProposalMother.aMeetupGroupProposalPendingOfApproval()
        val memberUser = UserMother.aMemberUser()

        val exception = shouldThrow<MeetupGroupProposalCannotBeRejectedException> {
            meetupGroupProposalPendingOfApproval.reject(memberUser)
        }

        exception.message shouldBe "User '${memberUser.id.value}' is not Admin and cannot reject the proposal."
    }

    @Test
    fun `fail to reject a already rejected meetup group proposal`() {
        val meetupGroupProposalRejected = MeetupGroupProposalMother.aMeetupGroupProposalRejected()
        val memberUser = UserMother.aMemberUser()

        val exception = shouldThrow<MeetupGroupProposalAlreadyRejectedException> {
            meetupGroupProposalRejected.reject(memberUser)
        }

        exception.message shouldBe "Meetup group proposal '${meetupGroupProposalRejected.id}' already rejected."
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
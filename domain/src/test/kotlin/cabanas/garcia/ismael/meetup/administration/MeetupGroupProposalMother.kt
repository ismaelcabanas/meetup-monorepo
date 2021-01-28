package cabanas.garcia.ismael.meetup.administration

import java.time.Instant

object MeetupGroupProposalMother {
    private const val SOME_ID = "some id"
    private const val SOME_USER_ID = "some id"
    private const val SOME_COUNTRY = "some country"
    private const val SOME_CITY = "some city"
    private const val SOME_NAME = "some name"
    private const val SOME_DESCRIPTION = "some description"
    private const val SOME_DATE = "2021-01-25T13:13:03Z"

    fun aMeetupGroupProposalPendingOfApproval(): MeetupGroupProposal =
        MeetupGroupProposal(
            MeetupGroupProposalId(SOME_ID),
            SOME_USER_ID,
            SOME_NAME,
            SOME_DESCRIPTION,
            MeetupLocation(SOME_COUNTRY, SOME_CITY),
            Instant.parse(SOME_DATE)
        )

    fun aMeetupGroupProposalApproved(): MeetupGroupProposal {
        val meetupGroupProposalPendingOfApproval = aMeetupGroupProposalPendingOfApproval()

        return meetupGroupProposalPendingOfApproval.proposal().approve(User(UserId(SOME_USER_ID), "ADMIN"))
    }

    fun aMeetupGroupProposalRejected(): MeetupGroupProposal {
        val meetupGroupProposalPendingOfApproval = aMeetupGroupProposalPendingOfApproval()

        return meetupGroupProposalPendingOfApproval.proposal().reject(
            User(UserId(SOME_USER_ID), "ADMIN"),
            "some reason"
        )
    }
}
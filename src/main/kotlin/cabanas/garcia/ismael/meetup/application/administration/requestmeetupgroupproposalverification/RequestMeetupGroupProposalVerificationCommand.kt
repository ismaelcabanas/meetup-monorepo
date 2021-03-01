package cabanas.garcia.ismael.meetup.application.administration.requestmeetupgroupproposalverification

import java.time.Instant

data class RequestMeetupGroupProposalVerificationCommand(
    val meetupGroupProposalId: String,
    val proposalUserId: String,
    val name: String,
    val description: String,
    val country: String,
    val city: String,
    val proposalDate: Instant
)

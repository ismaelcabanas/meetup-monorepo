package cabanas.garcia.ismael.meetup.administration.application.requestmeetupgroupproposalverification

import cabanas.garcia.ismael.meetup.shared.application.Command
import java.time.Instant

data class RequestMeetupGroupProposalVerificationCommand(
    val meetupGroupProposalId: String,
    val proposalUserId: String,
    val name: String,
    val description: String,
    val country: String,
    val city: String,
    val proposalDate: Instant
) : Command

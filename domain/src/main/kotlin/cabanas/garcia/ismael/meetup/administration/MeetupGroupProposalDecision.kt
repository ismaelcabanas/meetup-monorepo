package cabanas.garcia.ismael.meetup.administration

import java.time.Instant

data class MeetupGroupProposalDecision(
    val date: Instant? = null,
    val rejectedReason: String? = null,
    val code: String? = null
) {
    fun accept(decisionDate: Instant, decisionUser: User): MeetupGroupProposalDecision {
        if (!decisionUser.isAdmin()) {
            throw MeetupGroupProposalCannotBeApprovedException(decisionUser.id)
        }
        return MeetupGroupProposalDecision(date = decisionDate, rejectedReason = null, code = "ACCEPT")
    }

    fun statusForDecision(): MeetupGroupProposalStatus {
        if (code == "ACCEPT") {
            return MeetupGroupProposalStatus.APPROVED
        } else if (code == "REJECT") {
            return MeetupGroupProposalStatus.REJECTED
        }
        return MeetupGroupProposalStatus.PENDING_OF_APPROVAL
    }

    fun reject(decisionDate: Instant, decisionUser: User, rejectedReason: String): MeetupGroupProposalDecision {
        if (!decisionUser.isAdmin()) {
            throw MeetupGroupProposalCannotBeRejectedException(decisionUser.id)
        }
        if (rejectedReason.isNullOrBlank()) {
            throw MeetupGroupProposalRequireReasonException()
        }

        return MeetupGroupProposalDecision(decisionDate, rejectedReason, "REJECT")
    }
}

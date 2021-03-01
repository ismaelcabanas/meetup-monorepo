package cabanas.garcia.ismael.meetup.domain.administration

interface MeetupGroupProposalState {
    fun pendingApprove(meetupGroupProposal: MeetupGroupProposal): MeetupGroupProposal

    fun approve(meetupGroupProposal: MeetupGroupProposal, approveBy: User): MeetupGroupProposal

    fun reject(meetupGroupProposal: MeetupGroupProposal, rejectBy: User, rejectedReason: String): MeetupGroupProposal

    fun value(): MeetupGroupProposalStatus
}
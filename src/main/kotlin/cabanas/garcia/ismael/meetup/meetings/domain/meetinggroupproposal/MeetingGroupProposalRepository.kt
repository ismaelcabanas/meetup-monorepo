package cabanas.garcia.ismael.meetup.meetings.domain.meetinggroupproposal

interface MeetingGroupProposalRepository {
    fun save(meetingGroupProposal: MeetingGroupProposal)
}
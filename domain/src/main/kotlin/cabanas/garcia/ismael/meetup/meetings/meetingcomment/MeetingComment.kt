package cabanas.garcia.ismael.meetup.meetings.meetingcomment

import cabanas.garcia.ismael.meetup.meetings.meeting.MeetingId
import cabanas.garcia.ismael.meetup.meetings.member.MemberId
import cabanas.garcia.ismael.meetup.useraccess.userregistration.DomainEvent

data class MeetingComment(
    val id: MeetingCommentId,
    val meetingId: MeetingId,
    val memberId: MemberId,
    val comment: String
) {
    private var events = mutableListOf<DomainEvent>()

    fun events(): List<DomainEvent> {
        return listOf(
            MeetingCommentCreated(
                id.value,
                meetingId.value,
                memberId.value,
                comment
            )
        )
    }

    fun registerDomainEvent(domainEvent: DomainEvent) {
        events.add(domainEvent)
    }

}

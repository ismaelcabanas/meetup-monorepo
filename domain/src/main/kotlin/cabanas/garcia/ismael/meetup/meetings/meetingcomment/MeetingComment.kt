package cabanas.garcia.ismael.meetup.meetings.meetingcomment

import cabanas.garcia.ismael.meetup.meetings.configuration.MeetingGroupConfiguration
import cabanas.garcia.ismael.meetup.meetings.meeting.MeetingId
import cabanas.garcia.ismael.meetup.meetings.meetinggroup.MeetingGroup
import cabanas.garcia.ismael.meetup.meetings.member.MemberId
import cabanas.garcia.ismael.meetup.useraccess.userregistration.DomainEvent
import java.time.Instant

class MeetingComment(
    val id: MeetingCommentId,
    val meetingId: MeetingId,
    val authorId: MemberId,
    val comment: Comment,
    meetingGroup: MeetingGroup,
    meetingGroupConfiguration: MeetingGroupConfiguration,
    val date: Instant = Instant.now()
) {
    private var events = mutableListOf<DomainEvent>()

    init {
        if (!meetingGroup.isMemberMeetingGroup(authorId)) {
            throw CommentCanBeAddedOnlyByMeetingGroupMembersException(authorId, meetingGroup.id)
        }
        if (meetingGroupConfiguration.isEnableComments) {
            throw CommentCanBeAddedIfCommentMeetingIsEnableException()
        }
    }

    fun events(): List<DomainEvent> = events

    fun create(): MeetingComment {
        registerDomainEvent(
            MeetingCommentCreated(
                id.value,
                meetingId.value,
                authorId.value,
                comment.value,
                date
            )
        )

        return this
    }

    private fun registerDomainEvent(domainEvent: DomainEvent) {
        events.add(domainEvent)
    }

}

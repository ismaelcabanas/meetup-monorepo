package cabanas.garcia.ismael.meetup.domain.meetings.meetingcomment

import cabanas.garcia.ismael.meetup.domain.meetings.configuration.MeetingGroupConfiguration
import cabanas.garcia.ismael.meetup.domain.meetings.meeting.MeetingId
import cabanas.garcia.ismael.meetup.domain.meetings.meetinggroup.MeetingGroup
import cabanas.garcia.ismael.meetup.domain.meetings.member.MemberId

object MeetingCommentFactory {
    fun create(
        meetingCommentId: MeetingCommentId,
        meetingId: String,
        authorId: MemberId,
        comment: String,
        meetingGroup: MeetingGroup,
        meetingGroupConfiguration: MeetingGroupConfiguration
    ): MeetingComment =
        MeetingComment(
            meetingCommentId,
            MeetingId(meetingId),
            authorId,
            Comment(comment),
            meetingGroup,
            meetingGroupConfiguration
        ).create()
}
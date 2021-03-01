package cabanas.garcia.ismael.meetup.meetings.domain.meetingcomment

import cabanas.garcia.ismael.meetup.meetings.domain.configuration.MeetingGroupConfiguration
import cabanas.garcia.ismael.meetup.meetings.domain.meeting.MeetingId
import cabanas.garcia.ismael.meetup.meetings.domain.meetinggroup.MeetingGroup
import cabanas.garcia.ismael.meetup.meetings.domain.member.MemberId

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
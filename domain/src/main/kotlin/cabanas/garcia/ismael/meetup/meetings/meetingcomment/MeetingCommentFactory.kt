package cabanas.garcia.ismael.meetup.meetings.meetingcomment

import cabanas.garcia.ismael.meetup.meetings.configuration.MeetingGroupConfiguration
import cabanas.garcia.ismael.meetup.meetings.meeting.MeetingId
import cabanas.garcia.ismael.meetup.meetings.meetinggroup.MeetingGroup
import cabanas.garcia.ismael.meetup.meetings.member.MemberId

object MeetingCommentFactory {
    fun create(
        meetingCommentId: String,
        meetingId: String,
        authorId: String,
        comment: String,
        meetingGroup: MeetingGroup,
        meetingGroupConfiguration: MeetingGroupConfiguration
    ): MeetingComment =
        MeetingComment(
            MeetingCommentId(meetingCommentId),
            MeetingId(meetingId),
            MemberId(authorId),
            Comment(comment),
            meetingGroup,
            meetingGroupConfiguration
        ).create()
}
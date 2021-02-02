package cabanas.garcia.ismael.meetup.meetings.meeting

import cabanas.garcia.ismael.meetup.meetings.configuration.MeetingGroupConfiguration
import cabanas.garcia.ismael.meetup.meetings.meetingcomment.MeetingComment
import cabanas.garcia.ismael.meetup.meetings.meetingcomment.MeetingCommentFactory
import cabanas.garcia.ismael.meetup.meetings.meetingcomment.MeetingCommentId
import cabanas.garcia.ismael.meetup.meetings.meetinggroup.MeetingGroup
import cabanas.garcia.ismael.meetup.meetings.member.MemberId

data class Meeting(
    val id: MeetingId
) {

    fun addComment(
        meetingCommentId: String,
        authorId: String,
        comment: String,
        meetingGroup: MeetingGroup,
        meetingGroupConfiguration: MeetingGroupConfiguration
    ): MeetingComment =
        MeetingCommentFactory.create(
            meetingCommentId,
            id.value,
            authorId,
            comment,
            meetingGroup,
            meetingGroupConfiguration
        )

}

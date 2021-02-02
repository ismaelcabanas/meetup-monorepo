package cabanas.garcia.ismael.meetup.meetings.configuration

import cabanas.garcia.ismael.meetup.meetings.meeting.MeetingId

data class MeetingGroupConfiguration(
    val meetingId: MeetingId,
    val commentsConfiguration: CommentsConfiguration
) {
    val isEnableComments: Boolean = CommentsConfiguration.ON == commentsConfiguration
}

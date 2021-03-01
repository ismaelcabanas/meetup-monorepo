package cabanas.garcia.ismael.meetup.meetings.domain.configuration

import cabanas.garcia.ismael.meetup.meetings.domain.meeting.MeetingId

data class MeetingGroupConfiguration(
    val meetingId: MeetingId,
    val commentsConfiguration: CommentsConfiguration
) {
    val isEnableComments: Boolean = CommentsConfiguration.ON == commentsConfiguration
}

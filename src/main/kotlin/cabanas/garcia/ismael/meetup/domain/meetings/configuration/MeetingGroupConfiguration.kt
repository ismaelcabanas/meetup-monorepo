package cabanas.garcia.ismael.meetup.domain.meetings.configuration

import cabanas.garcia.ismael.meetup.domain.meetings.meeting.MeetingId

data class MeetingGroupConfiguration(
    val meetingId: MeetingId,
    val commentsConfiguration: CommentsConfiguration
) {
    val isEnableComments: Boolean = CommentsConfiguration.ON == commentsConfiguration
}

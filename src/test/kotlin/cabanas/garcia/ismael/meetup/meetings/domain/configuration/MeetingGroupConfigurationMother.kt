package cabanas.garcia.ismael.meetup.meetings.domain.configuration

import cabanas.garcia.ismael.meetup.meetings.domain.meeting.MeetingId

object MeetingGroupConfigurationMother {
    private const val SOME_MEETING_ID = "some meeting id"

    fun withCommentsDisabled(): MeetingGroupConfiguration =
        MeetingGroupConfiguration(MeetingId(SOME_MEETING_ID), CommentsConfiguration.ON)

    fun withCommentsEnabled(): MeetingGroupConfiguration =
        MeetingGroupConfiguration(MeetingId(SOME_MEETING_ID), CommentsConfiguration.OFF)
}
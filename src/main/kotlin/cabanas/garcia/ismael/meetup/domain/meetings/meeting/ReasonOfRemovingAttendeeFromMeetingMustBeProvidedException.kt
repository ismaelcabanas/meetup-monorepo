package cabanas.garcia.ismael.meetup.domain.meetings.meeting

import cabanas.garcia.ismael.meetup.domain.meetings.member.MemberId

data class ReasonOfRemovingAttendeeFromMeetingMustBeProvidedException(
    val meetingId: MeetingId,
    val attendeeId: MemberId
)
    : Exception("Reason of removing attendee '${attendeeId.value}' from meeting '${meetingId.value}' must be provided.") {

}

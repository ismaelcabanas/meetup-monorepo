package cabanas.garcia.ismael.meetup.meetings.domain.meeting

import cabanas.garcia.ismael.meetup.meetings.domain.member.MemberId

data class ReasonOfRemovingAttendeeFromMeetingMustBeProvidedException(
    val meetingId: MeetingId,
    val attendeeId: MemberId
)
    : Exception("Reason of removing attendee '${attendeeId.value}' from meeting '${meetingId.value}' must be provided.") {

}

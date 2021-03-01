package cabanas.garcia.ismael.meetup.domain.meetings.meeting

import cabanas.garcia.ismael.meetup.domain.meetings.member.MemberId

data class OnlyMeetingAttendeesCanBeRemovedException(
    val meetingId: MeetingId,
    val attendeeId: MemberId)
    : Exception("Member '${attendeeId.value}' is not an attendee of meeting '${meetingId.value}'.")
